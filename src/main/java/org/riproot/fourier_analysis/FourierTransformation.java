package org.riproot.fourier_analysis;

import java.util.HashMap;

// https://www.nayuki.io/page/how-to-implement-the-discrete-fourier-transform

public class FourierTransformation {
    /*
     * The interpretation of the DFT is that the vector x represents the signal level at various points in time.
     * The vector X represents the signal level at various frequencies.
     * The formula outlines that the signal level at frequency k is equal to the sum of the 
     * signal level at each time t multiplied by a complex exponential.
     * 
     * 
     * The output of a Fourier Transformation is called a frequency spectrum, which contains a series of complex numbers where each number 
     * corresponds to a specific frequnecy and provides information about it's amplitude. (How much of that frequency is present) and phase 
     * (The offset of the frequency's waveform). Each output is in the form a + jb.    
     * 
     * After implementing it myself, I will move to the Apache Commons Math FFT. 
     * 
     * This will be part of a larger project called ASR. 
     * 
     * Pretty much I will:
     * 
     * Preprocess the audio (remove noise, normalize it)
     * 
     * Frame-Blocking (divide audio into short, overlapping timeframes)
     * 
     * Compute a FFT
     * 
     * Extract Features
     * 
     * Use a language model to convert the sequence into phonemes into recognizable words and sentances. //maybe
     */

     private double[] outReal;
     private double[] outImg; 

     public FourierTransformation(double[] inReal, double[] inImg){
        // Assuming that inReal and inImg have the same length.
        int n = inReal.length;
        double[] outR = new double[n];
        double[] outI = new double[n];

        for(int k = 0; k < n; k++){ // for each output k. (This is the Summation)
            double sumReal = 0;
            double sumImg = 0;
            for(int t = 0; t < n; t++){ // for each input element t. 
                /*  Euler's formula tells us that e^xi = cos x + i sin x for any real number x. 
                    cos -x = cos x, and sin -x = sin x.
                    So,
                    for a signal x(t), x(t)e^pi-2tk/n) = [Re(x(t)) + Im(x(t))][cos(pi2(tk/n)) - isin(pi2(tk/n))]
                */
                double angle = 2 * Math.PI * t * k / n; 
                sumReal = inReal[t] * Math.cos(angle) + inImg[t] * Math.sin(angle);
                sumImg = -inReal[t] * Math.sin(angle) + inImg[t] * Math.cos(angle); 


            }
            
            outR[k] = sumReal;
            outI[k] = sumImg;
        }

        this.outReal = outR;
        this.outImg = outI; 
     }

     public double[] getReal(){
        return outReal;
     }
     
     public double[] getImg(){
        return outImg;
     }


    // Returns a map of how many times each freq shows up in the arr.  
    public HashMap freqMap(double[] in){
        HashMap<Double, Integer> freqMap = new HashMap<>();
        
        for(double num : in){
            double yay = Math.round(num * 10000.0) / 10000.0;
            //System.out.println(yay);
            freqMap.put(yay, freqMap.getOrDefault(yay, 0) + 1);
        }

        return freqMap;
    }  
}
