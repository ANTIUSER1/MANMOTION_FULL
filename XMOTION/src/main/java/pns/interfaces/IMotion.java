/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pns.interfaces;

/**
 *
 * @author Movement
 */
public interface IMotion {

    public void motionFoward();

//    public void motionBackward();
    public void removePauseFoward();

//    public void removePauseBackward();
    public void motionPause();

    public void toStart() throws Exception;

    public void toEnd() throws Exception;

    public void stepForward(int currFrame) throws Exception;

    public void stepBackward(int currFrame) throws Exception;

}
