/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

/**
 * Add your docs here.
 */
public class LightPacket {

    int velocity;
    int[] color;
    int pos;

    public LightPacket(int p){
        pos = p;
    }

    public void setVelocity(int v){
        velocity = v;
    }

    public void setColor(int g, int r, int b){
        color[0] = g;
        color[1] = r;
        color[2] = b;
    }

    public void setPos(int p){
        pos = p;
    }

    public int getVelocity(){
        return velocity;
    }

    public int[] getColor(){
        return color;
    }

    public String getColorDat(){ //These are GRB, not RGB
        String s = "";
        s += color[0] + ",";
        s += color[1] + ",";
        s += color[2];
        return s;
    }

    public int getPos(){
        return pos;
    }

    public String getRangeDat(){
        return "" + pos;
    }
}
