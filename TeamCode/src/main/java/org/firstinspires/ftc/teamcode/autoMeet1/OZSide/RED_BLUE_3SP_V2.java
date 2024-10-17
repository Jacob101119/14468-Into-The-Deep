package org.firstinspires.ftc.teamcode.autoMeet1.OZSide;


import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BaseRobot;
import org.firstinspires.ftc.teamcode.Drive.MecanumDrive;




@Autonomous
public final class RED_BLUE_3SP_V2 extends LinearOpMode {

    BaseRobot robot;
    MecanumDrive drive;
    @Override


    public void runOpMode() throws InterruptedException{
        robot = new BaseRobot(hardwareMap, new Pose2d(12, -62.48, Math.toRadians(90)));

        robot.setGrasperPos(robot.getGRASPER_CLOSED());
        robot.updateGrasperPos();
        robot.setAxlePos(robot.getAXLE_SERVO_DOWN());
        robot.updateAxleServoPos();
        robot.setGimbalPos(robot.getGIMBAL_RESTING_POS());
        robot.updateGimbalPos();
















        waitForStart();
//new updates to run movements

        //TODO: check if this goes out of sizing
        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_TO_HIGH_CHAMBER());
        robot.updatePivotMotorPos();
        robot.delay(.6);
        robot.setSlidesPos(robot.getSLIDES_ABOVE_HIGH_RUNG());
        robot.updateSlidesPos();
        robot.setAxlePos(robot.getAXLE_SERVO_UP());
        robot.updateAxleServoPos();

        Action moveToSub = robot.drive.actionBuilder(robot.drive.pose)

                .strafeToConstantHeading(new Vector2d(9, -44.9))
                .build();
        Actions.runBlocking(moveToSub);//drive forward



        robot.delay(.3);

        robot.setSlidesPos(robot.getSLIDES_PUT_SP_ON_HIGH_RUNG());//clip specimen
        robot.updateSlidesPos();
        robot.delay(.5);


        robot.setGrasperPos(robot.getGRASPER_OPEN());//release specimen
        robot.updateGrasperPos();
        //robot.delay(.2);


        //moves sideways then back
        //TODO: fix the way it moves sideways to prevent getting stuck on specimen
        Action moveBackAwayFromSub = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToConstantHeading(new Vector2d(7, -44.9))
                .strafeToConstantHeading(new Vector2d(6, -53.00))
                .build();
        Actions.runBlocking(moveBackAwayFromSub);


        //slides down


        robot.setAxlePos(robot.getAXLE_SERVO_GRAB_FROM_WALL());
        robot.updateAxleServoPos();
        robot.setSlidesPos(0);//slides down
        robot.updateSlidesPos();
        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_GRAB_FROM_WALL()+170);
        robot.updatePivotMotorPos();



        //second specimen
        //_____________________________________________________________________________________________________

        //move to observation zone


        Action MoveToOZ = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(47.8, -40), Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(47.8, -43.8), Math.toRadians(-90))
                .build();
        Action updatedMoveToOZ = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(47.8, -43.8), Math.toRadians(-90))
                .build();

        Actions.runBlocking(MoveToOZ);//TODO: see if this new movement works without running into stuff
        robot.delay(.15);
        //grab specimen, pivot motor up
        robot.setGrasperPos(robot.getGRASPER_CLOSED());
        robot.updateGrasperPos();
        robot.delay(.2);
        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_VERTICAL());
        robot.updatePivotMotorPos();
        robot.delay(.2);//TODO: see if this is too little




        Action moveToSub2 = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(50.95, -41.00), Math.toRadians(-90.00))
                .strafeToLinearHeading(new Vector2d(30.17, -49.34), Math.toRadians(180.00))
                .strafeToLinearHeading(new Vector2d(9, -55), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(6, -46), Math.toRadians(90.00))
                .build();
        Action updatedMoveToSub2 = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(2, -46), Math.toRadians(90))
                .build();
        Actions.runBlocking(updatedMoveToSub2);//TODO: see if new path is ok
        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_TO_HIGH_CHAMBER());
        robot.updatePivotMotorPos();

        robot.delay(.3);//TODO: see if this too low


        robot.setSlidesPos(robot.getSLIDES_ABOVE_HIGH_RUNG());
        robot.updateSlidesPos();
        robot.delay(.4);

        robot.setAxlePos(robot.getAXLE_SERVO_UP());
        robot.updateAxleServoPos();
        robot.delay(.8);

        robot.setSlidesPos(robot.getSLIDES_PUT_SP_ON_HIGH_RUNG());//clip specimen
        robot.updateSlidesPos();
        robot.delay(.4);

        robot.setGrasperPos(robot.getGRASPER_OPEN());
        robot.updateGrasperPos();
        //robot.delay(.2);



        Action moveBackAwayFromSub2 = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToConstantHeading(new Vector2d(0, -50))
                .build();
        Actions.runBlocking(moveBackAwayFromSub2);

        robot.setAxlePos(robot.getAXLE_SERVO_GRAB_FROM_WALL());
        robot.updateAxleServoPos();
        robot.setSlidesPos(0);//slides down
        robot.updateSlidesPos();
        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_GRAB_FROM_WALL()+200);
        robot.updatePivotMotorPos();



        //second specimen
        //_____________________________________________________________________________________________________

        //move to observation zone

        Action MoveToOZ2 = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(18.00, -43.5), Math.toRadians(50.00))
                .strafeToLinearHeading(new Vector2d(31.11, -40), Math.toRadians(10.00))
                .strafeToLinearHeading(new Vector2d(49, -40), Math.toRadians(-90.00))
                .strafeToLinearHeading(new Vector2d(47.6, -43.7), Math.toRadians(-90.00))
                .build();
        Action updatedMoveToOZ2 = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(47.8, -36), Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(47.8, -43.8), Math.toRadians(-90))
                .build();
        Actions.runBlocking(updatedMoveToOZ2);//TODO: see if this new movement works without running into stuff
        robot.delay(.2);
        //grab specimen, pivot motor up
        robot.setGrasperPos(robot.getGRASPER_CLOSED());
        robot.updateGrasperPos();
        robot.delay(.2);
        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_VERTICAL());
        robot.updatePivotMotorPos();
        robot.delay(.2);//TODO: see if this is too little




        Action moveToSub3 = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(50.95, -41.00), Math.toRadians(-90.00))
                .strafeToLinearHeading(new Vector2d(30.17, -49.34), Math.toRadians(180.00))
                .strafeToLinearHeading(new Vector2d(9, -55), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(6, -46), Math.toRadians(90.00))
                .build();
        Action updatedMoveToSub3 = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(0, -46), Math.toRadians(90))
                .build();
        Actions.runBlocking(updatedMoveToSub3);//TODO: see if new path is ok
        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_TO_HIGH_CHAMBER());
        robot.updatePivotMotorPos();

        robot.delay(.3);//TODO: see if this too low


        robot.setSlidesPos(robot.getSLIDES_ABOVE_HIGH_RUNG());
        robot.updateSlidesPos();
        robot.delay(.3);

        robot.setAxlePos(robot.getAXLE_SERVO_UP());
        robot.updateAxleServoPos();
        robot.delay(.8);

        robot.setSlidesPos(robot.getSLIDES_PUT_SP_ON_HIGH_RUNG());//clip specimen
        robot.updateSlidesPos();
        robot.delay(.4);

        robot.setGrasperPos(robot.getGRASPER_OPEN());
        robot.updateGrasperPos();
        //robot.delay(.2);



        Action moveBackAwayFromSub3 = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToConstantHeading(new Vector2d(-2, -50))
                .build();
        Actions.runBlocking(moveBackAwayFromSub3);




        robot.resetAll();//reset method also updates all

        Action park = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToConstantHeading(new Vector2d(48, -51))
                .build();
        Actions.runBlocking(park);





    }
}
