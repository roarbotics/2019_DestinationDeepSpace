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
    int[] range;

    public LightPacket(int[] r){
        range = r;
    }

    public LightPacket(int l, int h){
        setRange(l, h);
    }

    public void setVelocity(int v){
        velocity = v;
    }

    public void setColor(int r, int g, int b){
        color[0] = r;
        color[1] = g;
        color[2] = b;
    }

    public void setRange(int l, int h){
        range[0] = l;
        range[1] = h;
    }

    public int getVelocity(){
        return velocity;
    }

    public int[] getColor(){
        return color;
    }

    public String getColorDat(){ //These are GRB, not RGB
        String s = "";
        s += color[0] + ".";
        s += color[1] + ".";
        s += color[2];
        return s;
    }

    public int[] getRange(){
        return range;
    }

    public String getRangeDat(){
        String s = "";
        s += range[0] + ".";
        s += range[1];
        return s;
    }
}
