package org.hipi.examples;

import org.hipi.image.FloatImage;
import org.hipi.image.HipiImageHeader;
import org.hipi.imagebundle.mapreduce.HibInputFormat;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.StringBuilder;

public class HelloWorld extends Configured implements Tool {
  
  public static class HelloWorldMapper extends Mapper<HipiImageHeader, FloatImage, IntWritable, FloatImage> {
    public void map(HipiImageHeader key, FloatImage value, Context context) 
      throws IOException, InterruptedException {
      if (value != null && value.getWidth() > 1 && value.getHeight() > 1 && value.getNumBands() == 3) {

        // Emit record to reducer
        context.write(new IntWritable(1), value);

      } // If (value != null...
    }
  }
  
  public static class HelloWorldReducer extends Reducer<IntWritable, FloatImage, IntWritable, Text> {
    public void reduce(IntWritable key, Iterable<FloatImage> values, Context context) 
      throws IOException, InterruptedException {
        

        List<float[][][]> res = new ArrayList<>();

      // Initialize a counter and iterate over IntWritable/FloatImage records from mapper
      int total = 0;
      for (FloatImage val : values) {// Get dimensions of image
        int w = val.getWidth();
        int h = val.getHeight();
        total++;
        float[][][] avgData = new float[h][w][3];
        float[] img = val.getData();
        for(int i=0; i<h; i++){
          for(int j=0; j<w; j++){
            for(int k=0; k<3; k++){
              avgData[i][j][k] = img[(i*w+j)*3+k];
            }
          }
        }
        res.add(avgData);
      }

      if (total > 0) {
        
        for(int i=0; i<res.size(); i++){
          // Emit output of job which will be written to HDFS
          context.write(new IntWritable(i), new Text(arrToString(res.get(i))));
        }
      }
    }
  }

  private static String arrToString(float[][][] x){
    StringBuilder sb = new StringBuilder();
    sb.append("[");   
    for(float[][] f1: x){
      sb.append("[");
      for(float[] f2: f1){
        sb.append("[");
        for(int i=0; i<f2.length; i++){
          sb.append(String.valueOf(f2[i]));
          if(i != f2.length-1)  sb.append(", ");
        }
        sb.append("]");
      }
      sb.append("]");
    }
    sb.append("]");
    return sb.toString();
  }
  
  public int run(String[] args) throws Exception {
    // Check input arguments
    if (args.length != 2) {
      System.out.println("Usage: helloWorld <input HIB> <output directory>");
      System.exit(0);
    }
    
    // Initialize and configure MapReduce job
    Job job = Job.getInstance();
    // Set input format class which parses the input HIB and spawns map tasks
    job.setInputFormatClass(HibInputFormat.class);
    // Set the driver, mapper, and reducer classes which express the computation
    job.setJarByClass(HelloWorld.class);
    job.setMapperClass(HelloWorldMapper.class);
    job.setReducerClass(HelloWorldReducer.class);
    // Set the types for the key/value pairs passed to/from map and reduce layers
    job.setMapOutputKeyClass(IntWritable.class);
    job.setMapOutputValueClass(FloatImage.class);
    job.setOutputKeyClass(IntWritable.class);
    job.setOutputValueClass(Text.class);
    
    // Set the input and output paths on the HDFS
    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    // Execute the MapReduce job and block until it complets
    boolean success = job.waitForCompletion(true);
    
    // Return success or failure
    return success ? 0 : 1;
  }
  
  public static void main(String[] args) throws Exception {
    ToolRunner.run(new HelloWorld(), args);
    System.exit(0);
  }
  
}