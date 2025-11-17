package org.riproot.fourier_analysis;

//Imported Packages
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;


public class SignalGenerator {
    
    private byte[] allAudioBytes;


    public SignalGenerator(File wavFile) {
        /*
         * Since I am using .wav files, and they are bytes by default, I will just be copying that data into a byte array. 
         * 
         * 
         * A WAV file contains a header followed by raw data, so in order to get the raw data we will use the getBytes method from the 
         * AudioInputStream class. 
         */
        AudioInputStream audio = null;

        //The line below will help write data into a byte array. 
        ByteArrayOutputStream ba = new ByteArrayOutputStream();


        try {
            audio = AudioSystem.getAudioInputStream(wavFile); 
            
            byte[] Buffer = new byte[4096]; //52000 bytes is about 52 kilobytes which is how much storage the Carlitos_Way_Watching file is. 
            //While loop will run until there are no more bytes in Carlitos_Way_Watching. 

            int bytesRead; //temp variable
            
            while((bytesRead = audio.read(Buffer)) != -1){ 
                // What audio.read(byte[] b) does is that it reads the bytes all the way until the length of the array inputed as bytes. 
                ba.write(Buffer, 0, bytesRead); // Here, I am taking my bytesRead func and putting it into my Buffer array. 
            }

            byte[] allAudioBytes = ba.toByteArray();
            this.allAudioBytes = allAudioBytes;

        } catch (Exception e) {
            allAudioBytes = null;
            e.getStackTrace();
        }
    }

    public SignalGenerator(){
        // Overloading constructions to make another object that only uses the generateSineWave/ other possible functions in it when missing .wav function
    }

    public byte[] getAllAudioBytes(){
        return allAudioBytes;
    }

    
    public double[] generateSineWave(double amplitude, double frequency, int numSamples) {
        /*
         * This function will create a sine wave with inputed (amplitude, freq, and numsmaples)
         */
        double[] sineWave = new double[numSamples];
        double sampleRate = 44100.0; // also explain samplerate later
        double angularFreq = 6.2 * frequency; // explain 6.2 later

        for(int i = 0; i < numSamples; ++i) {
            double time = (double)i / sampleRate;
            sineWave[i] = amplitude * Math.sin(angularFreq * time);
        }

        return sineWave;
    }

    /* 
    public double[] toDoubleArray(){
        * 
         * What is the fault of turning my .wav file byte[] to a double[]? what do I lose?
         * 1. Misinterpretation? 
         * 
         * 
         *

        //Create a ByteBuffer 4 wrapping the byte array
        ByteBuffer buffer = ByteBuffer.wrap(allAudioBytes);

        //Calculate the number of doubles in the byte array


    }
    */
}