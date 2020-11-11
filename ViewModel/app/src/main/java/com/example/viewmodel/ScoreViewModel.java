package com.example.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {
    private MutableLiveData<Integer> scoreA = new MutableLiveData<>();
    private MutableLiveData<Integer> scoreB = new MutableLiveData<>();

    public LiveData<Integer> getScoreA(){
        if(scoreA.getValue() == null){
            scoreA = new MutableLiveData<>();
            scoreA.setValue(0);
        }

        return scoreA;
    }

    public LiveData<Integer> getScoreB(){
        if(scoreB.getValue() == null){
            scoreB = new MutableLiveData<>();
            scoreB.setValue(0);
        }

        return scoreB;
    }

    public void addScoreA(){
        if(scoreA.getValue() != null) {
            scoreA.setValue(scoreA.getValue() + 1);
        }
    }

    public void minusScoreA(){
        if(scoreA.getValue() != null) {
            scoreA.setValue(scoreA.getValue() - 1);

            if(scoreA.getValue() < 0 ){
                scoreA.setValue(0);
            }
        }
    }

    public void addScoreB(){
        if(scoreB.getValue() != null) {
            scoreB.setValue(scoreB.getValue() + 1);
        }
    }

    public void minusScoreB(){
        if(scoreB.getValue() != null) {
            scoreB.setValue(scoreB.getValue() - 1);

            if(scoreB.getValue() < 0 ){
                scoreB.setValue(0);
            }
        }
    }

    public void reset(){
        scoreA.setValue(0);
        scoreB.setValue(0);
    }
}
