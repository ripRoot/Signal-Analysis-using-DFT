/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.riproot.fourier_analysis;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        File wavFiles = new File("../../audioFiles/nepblue24s10x.wav");
        
        // Signal Generator Object using .wav file bytes. 
        SignalGenerator a = new SignalGenerator(wavFiles);

        // This byte[] is represented in a time domain. Whenever we compute a fourier transformation on it, we transform it to a frequency domain.
        // Like a white noise signal on the time domain with equal amplitudes at each time will result in a
        // straight line on the frequency domain cause each amplitude is equal.
        byte[] AudioBytes = a.getAllAudioBytes();
        
        


        // Signal Generator (empty constructor test) using a generated sine wave
        SignalGenerator b = new SignalGenerator();


        double[] sinewave = b.generateSineWave(1.0, 1.0, 100);
        // Empty 
        double[] imagSinewave = new double[sinewave.length];
        
        FourierTransformation dft = new FourierTransformation(sinewave, imagSinewave);

        double[] dftSineReal = dft.getReal();
        double[] dftSineImag = dft.getImg();
        
        HashMap<Double, Integer> freqMap = dft.freqMap(dftSineReal);
        
        for(Map.Entry<Double, Integer> entry : freqMap.entrySet()){
            System.out.println("FrequencyKey: " + entry.getKey() + " FrequencyValue: " + entry.getValue());
        }
    }
}
