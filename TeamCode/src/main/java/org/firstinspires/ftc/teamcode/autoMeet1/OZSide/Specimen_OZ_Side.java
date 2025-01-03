package org.firstinspires.ftc.teamcode.autoMeet1.OZSide;


import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BaseRobot;
import org.firstinspires.ftc.teamcode.Drive.MecanumDrive;




@Autonomous
public final class Specimen_OZ_Side extends LinearOpMode {

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



        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_TO_HIGH_CHAMBER());
        robot.updatePivotMotorPos();
        robot.delay(.6);
        robot.setSlidesPos(robot.getSLIDES_ABOVE_HIGH_RUNG());
        robot.updateSlidesPos();
        robot.setAxlePos(robot.getAXLE_SERVO_UP());
        robot.updateAxleServoPos();

        Action moveToSub = robot.drive.actionBuilder(robot.drive.pose)

                .strafeToConstantHeading(new Vector2d(2, -42.8))
                .build();
        Actions.runBlocking(moveToSub);//drive forward

        telemetry.addData("pose", Math.toDegrees((robot.drive.pose.heading.toDouble())));
        telemetry.update();


        robot.delay(.3);

        //new
        telemetry.addLine("clip clip clip");
        telemetry.update();
        //end new

        robot.setSlidesPos(robot.getSLIDES_PUT_SP_ON_HIGH_RUNG());//clip specimen
        robot.updateSlidesPos();
        robot.delay(.35);


        robot.setGrasperPos(robot.getGRASPER_OPEN());//release specimen
        robot.updateGrasperPos();

        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_TO_HIGH_CHAMBER());
        robot.updatePivotMotorPos();
        robot.delay(.2);
        //robot.delay(.2);


        //moves sideways then back
        //TODO: fix the way it moves sideways to prevent getting stuck on specimen
        Action moveBackAwayFromSub = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToConstantHeading(new Vector2d(-4, -41.4))
                .strafeToConstantHeading(new Vector2d(-4, -53.00))
                .build();
        Actions.runBlocking(moveBackAwayFromSub);


        //slides down



        //second specimen
        //_____________________________________________________________________________________________________

        //move to observation zone



        robot.setAxlePos(robot.getAXLE_SERVO_GRAB_FROM_WALL());
        robot.updateAxleServoPos();
        robot.setSlidesPos(0);//slides down
        robot.updateSlidesPos();
        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_GRAB_FROM_WALL()-230);
        robot.updatePivotMotorPos();



        //second specimen
        //_____________________________________________________________________________________________________

        //move to observation zone



        Action updatedMoveToOZ = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(46.8, -40), Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(46.8, -43.7), Math.toRadians(-90))
                .build();
        Actions.runBlocking(updatedMoveToOZ);
        robot.delay(.5);
        //grab specimen, pivot motor up

        //new
        telemetry.addLine("let the local boy cook");
        telemetry.addLine();
        telemetry.addLine();
        telemetry.addLine();
        telemetry.addData("x:", robot.drive.pose.position.x);
        telemetry.addData("y:", robot.drive.pose.position.y);
        telemetry.addData("heading:", robot.drive.pose.heading.toDouble());

        telemetry.update();
        //end new

        robot.setGrasperPos(robot.getGRASPER_CLOSED());
        robot.updateGrasperPos();
        robot.delay(.2);
        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_VERTICAL() + 40);
        robot.updatePivotMotorPos();
        robot.delay(.2);//TODO: see if this is too little




        Action moveToSub2 = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(50.95, -41.00), Math.toRadians(-90.00))
                .strafeToLinearHeading(new Vector2d(30.17, -49.34), Math.toRadians(180.00))
                .strafeToLinearHeading(new Vector2d(9, -55), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(6, -46), Math.toRadians(90.00))
                .build();
        Action updatedMoveToSub2 = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(4, -44.8), Math.toRadians(90))
                .build();
        Actions.runBlocking(updatedMoveToSub2);//TODO: see if new path is ok
        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_TO_HIGH_CHAMBER()-250);
        robot.updatePivotMotorPos();

        robot.delay(.3);//TODO: see if this too low


        robot.setSlidesPos(robot.getSLIDES_ABOVE_HIGH_RUNG()+50);
        robot.updateSlidesPos();
        robot.delay(.4);

        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_TO_HIGH_CHAMBER()-90);
        robot.updatePivotMotorPos();
        robot.delay(.5);

        robot.setAxlePos(robot.getAXLE_SERVO_UP());
        robot.updateAxleServoPos();
        robot.delay(.8);

        //new
        telemetry.addLine("clip clip clip");
        telemetry.update();
        //end new

        robot.setSlidesPos(robot.getSLIDES_PUT_SP_ON_HIGH_RUNG());//clip specimen
        robot.updateSlidesPos();
        robot.delay(.4);

        robot.setGrasperPos(robot.getGRASPER_OPEN());
        robot.updateGrasperPos();
        //robot.delay(.2);


        robot.resetAll();



        Action moveBackAwayFromSub2ToRED = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToConstantHeading(new Vector2d(-2, -41.5))
                //.strafeToConstantHeading(new Vector2d(8, -53))
                .strafeToConstantHeading(new Vector2d(35.3, -46))
                //.strafeToConstantHeading(new Vector2d(35.3, -7), new TranslationalVelConstraint(70), new ProfileAccelConstraint(-70,70))
                .strafeToConstantHeading(new Vector2d(35.3, -9))

                .strafeToLinearHeading(new Vector2d(46.5, - 8), Math.toRadians(90))//move to sample 1
                .strafeToLinearHeading(new Vector2d(48.5, - 55), Math.toRadians(90))//push to OZ
                .strafeToLinearHeading(new Vector2d(45, - 8), Math.toRadians(90))//move to sample 2
                .strafeToLinearHeading(new Vector2d(55.5, - 8), Math.toRadians(90))//move to sample 2
                .strafeToLinearHeading(new Vector2d(48, - 52), Math.toRadians(90), new TranslationalVelConstraint(100), new ProfileAccelConstraint(-100 , 100))//push to OZ



/*
                .strafeToConstantHeading(new Vector2d(-2, -41.5))
                //.strafeToConstantHeading(new Vector2d(8, -53))
                .strafeToConstantHeading(new Vector2d(35.3, -46))
                //.strafeToConstantHeading(new Vector2d(35.3, -7), new TranslationalVelConstraint(70), new ProfileAccelConstraint(-70,70))
                .strafeToConstantHeading(new Vector2d(35.3, -7))

                .strafeToLinearHeading(new Vector2d(47, - 8), Math.toRadians(-90))//move to sample 1
                .strafeToLinearHeading(new Vector2d(49, - 55), Math.toRadians(-90))//push to OZ
                .strafeToLinearHeading(new Vector2d(45, - 8), Math.toRadians(-90))//move to sample 2
                .strafeToLinearHeading(new Vector2d(54, - 8), Math.toRadians(-90))//move to sample 2
                .strafeToLinearHeading(new Vector2d(48, - 48), Math.toRadians(-90), new TranslationalVelConstraint(100), new ProfileAccelConstraint(-100 , 100))//push to OZ
                */


                .build();
        Actions.runBlocking(moveBackAwayFromSub2ToRED);

        //robot.setPivotMotorPos(4500);
        //robot.updatePivotMotorPos();










    }
}

