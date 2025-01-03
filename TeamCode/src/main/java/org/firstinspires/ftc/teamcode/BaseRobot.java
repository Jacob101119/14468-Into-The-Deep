package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Drive.MecanumDrive;


public class BaseRobot{

    // Arm Position fields
    private int leftSliderPos = 0;
    private int rightSliderPos = 0;
    private int pivotMotorPos = 0;
    private int hangArmPos = 0;
    //end arm pos fields

    //servo positions
    private double gimbalPos = 0;
    private double axlePos = 0;
    private double grasperPos = 0;
    //end servo positions





    //private int deltaLeftPos = 0;
    //private int deltaRightPos = 0;

    //motor powers
    private double PIVOT_MOTOR_POWER = 0.5;
    private double LEFT_SLIDE_POWER = 0.9;
    private double RIGHT_SLIDE_POWER = 0.9;
    private double HANG_ARM_POWER = 0.8;
    //end

    // servo Constants
    double GRASPER_WIDE_OPEN = .7;
    double GRASPER_HALF_OPEN = .6;
    double GRASPER_CLOSED = .4;
    double AXLE_SERVO_BACK = 0;
    double AXLE_SERVO_UP = .343;
    double AXLE_SERVO_DOWN = .664;
    double AXLE_SERVO_GRAB_FROM_WALL = .05;//may need minor adjustments

    double GIMBAL_BASKET_SCORING = .55;
    double GIMBAL_SPECIMEN_SCORING = .55;
    double GIMBAL_RESTING_POS = .407;

    //double LEFT_GRASPER_OPEN = 0;//change
    //double LEFT_GRASPER_CLOSED = 0;//change
    //double RIGHT_GRASPER_OPEN = 0;//change
    //double RIGHT_GRASPER_CLOSED = 0;//change



    //end servo constants

    //motor constants
    int SLIDES_ABOVE_HIGH_RUNG = 900;//change
    int SLIDES_PUT_SP_ON_HIGH_RUNG = 50;//change
    int SLIDES_MAX = 3110;//change
    int SLIDES_MIN = 0;
    int SLIDES_TO_SUB = 60;//change

    int PIVOT_MOTOR_TO_SUB = 0;//change
    //int PIVOT_MOTOR_VERTICAL = 2248;//old before gearbox change, should be the same
    int PIVOT_MOTOR_VERTICAL = 2848;//after gearbox change
    int PIVOT_MOTOR_HORIZONTAL = 5003;
    int PIVOT_MOTOR_GRAB_FROM_WALL = 5100;
    int PIVOT_MOTOR_TO_HIGH_CHAMBER = 3148;//maybe 3148
    //end motor constants

    public MecanumDrive drive;

    DcMotor leftSlider;//from the perspective of the robot
    DcMotor rightSlider;//from the perspective of the robot
    DcMotor pivotMotor;//worm gear box
    DcMotor hangArm;//monkey arm

    //servos
    Servo grasper = null;
    Servo grasperGimbal = null;
    Servo axleRotation = null;

    Servo leftGrasper = null;//unused
    Servo rightGrasper = null;//unused
    //end servos


    public BaseRobot(HardwareMap hwMap){
        this(hwMap, new Pose2d(0,0,0));
    }

    public BaseRobot(HardwareMap hwMap, Pose2d pose){

        drive = new MecanumDrive(hwMap, pose);

        hangArm = hwMap.dcMotor.get("hangArm");


        pivotMotor = hwMap.dcMotor.get("pivotMotor");


        leftSlider = hwMap.dcMotor.get("leftSlider");
        rightSlider = hwMap.dcMotor.get("rightSlider");
        rightSlider.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set to run with encoders and grab current Position
        leftSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSliderPos = leftSlider.getCurrentPosition();

        rightSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSliderPos = rightSlider.getCurrentPosition();

        //hang arm
        hangArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hangArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hangArmPos = hangArm.getCurrentPosition();

        //pivot motor
        pivotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pivotMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pivotMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        pivotMotorPos = hangArm.getCurrentPosition();

        if(pivotMotorPos > PIVOT_MOTOR_VERTICAL + 200){
            SLIDES_MAX = 400;
        }

        //servos


        grasper = hwMap.servo.get("claw");
        grasper.setPosition(GRASPER_WIDE_OPEN);

        rightGrasper = hwMap.servo.get("rightGrasper");
        //rightGrasper.setPosition(RIGHT_GRASPER_OPEN);

        leftGrasper = hwMap.servo.get("leftGrasper");//unused
        //leftGrasper.setPosition(LEFT_GRASPER_OPEN);//unused

        grasperGimbal = hwMap.servo.get("grasperGimbal");
        grasperGimbal.setPosition(GIMBAL_RESTING_POS);

        axleRotation = hwMap.servo.get("axleRotation");
        axleRotation.setPosition(AXLE_SERVO_UP);
        //end servos



    }



    //hang arm pos --------------
    public void updateHangArmPos(){
        hangArm.setTargetPosition(hangArmPos);
        hangArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hangArm.setPower(HANG_ARM_POWER);
    }
    public void setHangArmPos(int newPos) {
        hangArmPos = newPos;
    }
    public void changeHangArmPos(int deltaPos){
        hangArmPos += deltaPos;
    }
    //end hang arm -----------------


    //pivot motor pos ------------------
    public void updatePivotMotorPos() {
        if (pivotMotorPos < 0){
            pivotMotorPos = 0;
        }

        if (pivotMotorPos > 4769){
            pivotMotorPos = 4769;
        }

        pivotMotor.setTargetPosition(pivotMotorPos);
        pivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivotMotor.setPower(PIVOT_MOTOR_POWER);
    }

    public void RESETUpdatePivotMotorPos(){
        //pivot motor position updating that has no limits, used for resetting the robot when value needs to be <0
        pivotMotor.setTargetPosition(pivotMotorPos);
        pivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivotMotor.setPower(PIVOT_MOTOR_POWER);
    }
    public void setPivotMotorPos(int newPos) {
        //if (newPos < 0){
          //  newPos = 0;
        //}
        pivotMotorPos = newPos;
    }
    public void changePivotMotorPos(int deltaPos){
        pivotMotorPos += deltaPos;
    }
    //end pivot motor pos ------------------

    //gimbal pos ----------------
    public void updateGimbalPos() {
        if(gimbalPos > 1){
            gimbalPos = 1;
        }
        if(gimbalPos < 0){
            gimbalPos = 0;
        }
        grasperGimbal.setPosition(gimbalPos);

    }
    public void setGimbalPos(double newPos){
        gimbalPos = newPos;
    }
    public void changeGimbalPos(double deltaPos){
        gimbalPos += deltaPos;
    }
    //end gimbal pos ------------


    //axle servo ---------
    public void updateAxleServoPos(){
        //if(pivotMotorPos < PIVOT_MOTOR_VERTICAL && axlePos > ) {
          //  axlePos = getAXLE_SERVO_UP();
        //}
        //if (pivotMotorPos < 600 && axlePos < AXLE_SERVO_UP){
          //  axlePos = getAXLE_SERVO_UP();
        //}
        if(axlePos > 1){
            axlePos = 1;
        }
        if(axlePos < 0){
            axlePos = 0;
        }
        if(pivotMotorPos < 1489 && axlePos < AXLE_SERVO_DOWN) {
            setAxlePos(AXLE_SERVO_DOWN);
        }


        axleRotation.setPosition(axlePos);
    }
    public void setAxlePos(double newPos){
        axlePos = newPos;
    }
    public void changeAxlePos(double deltaPos){
        axlePos += deltaPos;
    }
    //end axle servo ---------

    //grasper servo -------------
    public void updateGrasperPos(){

        if(grasperPos > 1){
            grasperPos = 1;
        }
        if(grasperPos < 0){
            grasperPos = 0;
        }
        grasper.setPosition(grasperPos);
    }
    public void setGrasperPos(double newPos){
        grasperPos = newPos;
    }
    public void changeGrasperPos(double deltaPos){
        grasperPos += deltaPos;
    }
    //end grasper servo ----------


    //slides -----------------
    public void updateSlidesPos() {

        // Code from teleop that grabs armPos variables and
        // Uses run to position
        if(rightSliderPos > SLIDES_MAX){
            rightSliderPos = SLIDES_MAX;
        }
        if (leftSliderPos > SLIDES_MAX){
            leftSliderPos = SLIDES_MAX;
        }

        if(rightSliderPos < SLIDES_MIN){
            rightSliderPos = SLIDES_MIN;
        }
        if (leftSliderPos < SLIDES_MIN){
            leftSliderPos = SLIDES_MIN;
        }
        if(pivotMotorPos > PIVOT_MOTOR_VERTICAL +50 && leftSliderPos > 650){
            leftSliderPos = 650;//slides cant go far when pivot motor down
            rightSliderPos = 650;
        }
        if (pivotMotorPos < 600 && leftSliderPos > 0){
            leftSliderPos = 0;
            rightSliderPos = 0;
        }
        /*if(pivotMotorPos < PIVOT_MOTOR_VERTICAL + 800 && pivotMotorPos > PIVOT_MOTOR_VERTICAL-1000 && leftSliderPos > 2000){
             leftSliderPos = 2400;
             rightSliderPos = ;
        }

         */
        if(pivotMotorPos < getPIVOT_MOTOR_VERTICAL()-800 && leftSliderPos > 300){
            leftSliderPos = 300;//slides cant go up when pivot motor is back
            rightSliderPos = 300;
        }


        rightSlider.setTargetPosition(rightSliderPos);
        rightSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlider.setTargetPosition(leftSliderPos);
        leftSlider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftSlider.setPower(LEFT_SLIDE_POWER);
        rightSlider.setPower(RIGHT_SLIDE_POWER);

    }
    public void setSlidesPos(int newPos) {

        leftSliderPos = newPos;
        rightSliderPos = newPos;
        //rightSliderPos = newPos;
        // update the fields (variables) that hold armpos

    }
    public void changeSlidesPos(int deltaPos) {
        leftSliderPos += deltaPos;
        rightSliderPos += deltaPos;

        // update the fields (variables) by adding deltaPos

    }
    //end slides -----------------


    //end motor positions
    //____________________________________________________________________________________________________________________




    public void slidesUp(){
        setSlidesPos(SLIDES_MAX);
    }


    public void basketScoring(){
        setPivotMotorPos(PIVOT_MOTOR_VERTICAL);
        setSlidesPos(SLIDES_MAX);
        axleRotation.setPosition(AXLE_SERVO_UP);
        grasperGimbal.setPosition(GIMBAL_BASKET_SCORING);

    }
    public void specimenScoring(){
        pivotMotor.setTargetPosition(PIVOT_MOTOR_VERTICAL);
        setSlidesPos(SLIDES_MAX);//maybe a bit less
        axleRotation.setPosition(AXLE_SERVO_UP);
        grasperGimbal.setPosition(GIMBAL_SPECIMEN_SCORING);
    }
    public void slidesDown(){
        setSlidesPos(SLIDES_MIN);
    }
    public void reachToSub() {

        axleRotation.setPosition(AXLE_SERVO_DOWN);

        setPivotMotorPos(PIVOT_MOTOR_HORIZONTAL);
        //while(Math.abs(pivotMotor.getCurrentPosition()-PIVOT_MOTOR_HORIZONTAL)>200){

        //}
        setSlidesPos(SLIDES_TO_SUB);



        grasper.setPosition(GRASPER_WIDE_OPEN);
        grasperGimbal.setPosition(GIMBAL_RESTING_POS);
    }

    public void resetAll(){
        setGrasperPos(GRASPER_WIDE_OPEN);
        setSlidesPos(SLIDES_MIN);
        setGimbalPos(GIMBAL_RESTING_POS);
        setAxlePos(AXLE_SERVO_DOWN);
        setPivotMotorPos(0);
        updateAll();
    }

    public void updateAll(){
        //servos
        updateAxleServoPos();
        updateGrasperPos();
        updateGimbalPos();

        //motors
        updatePivotMotorPos();
        updateSlidesPos();

        //updateHangArmPos();
    }
    //hang arm

    public void slidesReset(){
        grasper.setPosition(GRASPER_CLOSED);
        setSlidesPos(SLIDES_MIN);
        grasperGimbal.setPosition(GIMBAL_RESTING_POS);
        axleRotation.setPosition(AXLE_SERVO_DOWN);
        setPivotMotorPos(2240);
    }
    public void slidesMax(){
        setSlidesPos(SLIDES_MAX);
    }
    //end hang arm

    //end presets
    //____________________________________________________________________________________________________________________


        public void resetSliderEncoders(){

            leftSlider.setPower(0);
            rightSlider.setPower(0);
            leftSliderPos=0;
            rightSliderPos=0;
            leftSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }



    // Accessors

    //MOTOR POSITIONS
    public int getRightSliderPos() {
        return rightSliderPos;
    }
    public int getLeftSliderPos(){
        return leftSliderPos;
    }
    public int getPivotMotorPos(){
        return pivotMotorPos;
    }
    public int getHangArmPos(){
        return hangArmPos;
    }
    //END MOTOR POSITIONS

    //SERVO POSITIONS
    public double getGimbalPos(){
        return gimbalPos;
    }
    public double getAxlePos() {
        return axlePos;
    }
    public double getGrasperPos(){
        return grasperPos;
    }
    //END SERVO POSITIONS

    //MOTOR POWERS
    public double getRIGHT_SLIDE_POWER(){
        return RIGHT_SLIDE_POWER;
    }
    public double getLEFT_SLIDE_POWER(){
        return LEFT_SLIDE_POWER;
    }
    public double getPIVOT_MOTOR_POWER(){
        return PIVOT_MOTOR_POWER;
    }
    //END MOTOR POWERS


    //AXLE SERVO
    public double getAXLE_SERVO_BACK(){
        return AXLE_SERVO_BACK;
    }
    public double getAXLE_SERVO_UP(){
        return AXLE_SERVO_UP;
    }
    public double getAXLE_SERVO_DOWN(){
        return AXLE_SERVO_DOWN;
    }
    public double getAXLE_SERVO_GRAB_FROM_WALL(){
        return AXLE_SERVO_GRAB_FROM_WALL;
    }
    //END AXLE SERVO

    //GRASPER
    public double getGRASPER_OPEN(){
        return GRASPER_WIDE_OPEN;
    }
    public double getGRASPER_HALF_OPEN(){
        return GRASPER_HALF_OPEN;
    }
    public double getGRASPER_CLOSED(){
        return GRASPER_CLOSED;
    }
    public double getGRASPER_WIDE_OPEN(){
        return GRASPER_WIDE_OPEN;
    }
    //END GRASPER

    //GIMBAL SERVO
    public double getGIMBAL_BASKET_SCORING(){
        return GIMBAL_BASKET_SCORING;
    }
    public double getGIMBAL_SPECIMEN_SCORING(){
        return GIMBAL_SPECIMEN_SCORING;
    }
    public double getGIMBAL_RESTING_POS(){
        return GIMBAL_RESTING_POS;
    }
    //END GIMBAL SERVO

    //SLIDES

    public int getSLIDES_ABOVE_HIGH_RUNG(){
        return SLIDES_ABOVE_HIGH_RUNG;
    }
    public int getSLIDES_PUT_SP_ON_HIGH_RUNG(){
        return SLIDES_PUT_SP_ON_HIGH_RUNG;
    }
    public int getSLIDES_MAX(){
        return SLIDES_MAX;
    }
    public int getSLIDES_MIN(){
        return SLIDES_MIN;
    }
    public int getSLIDES_TO_SUB(){
        return SLIDES_TO_SUB;
    }
    //END SLIDES

    //PIVOT MOTOR
    public int getPIVOT_MOTOR_HORIZONTAL(){
        return PIVOT_MOTOR_HORIZONTAL;
    }
    public int getPIVOT_MOTOR_VERTICAL(){
        return PIVOT_MOTOR_VERTICAL;
    }
    public int getPIVOT_MOTOR_TO_SUB(){
        return PIVOT_MOTOR_TO_SUB;
    }
    public int getPIVOT_MOTOR_GRAB_FROM_WALL(){
        return PIVOT_MOTOR_GRAB_FROM_WALL;
    }
    public int getPIVOT_MOTOR_TO_HIGH_CHAMBER(){
        return PIVOT_MOTOR_TO_HIGH_CHAMBER;
    }
    //END PIVOT MOTOR

    //end accessors
    //____________________________________________________________________________________________________________________

    public void delay(double seconds){
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while(timer.seconds() < seconds){

        }

    }


}
