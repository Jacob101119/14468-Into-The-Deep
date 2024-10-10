package org.firstinspires.ftc.teamcode.autoMeet1.BLUE.BucketSide;


import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.BaseRobot;
import org.firstinspires.ftc.teamcode.Drive.MecanumDrive;




@Autonomous
public final class BLUE_SP_2Y_HB extends LinearOpMode {

    BaseRobot robot;
    MecanumDrive drive;
    @Override


    public void runOpMode() throws InterruptedException{
        robot = new BaseRobot(hardwareMap, new Pose2d(12, 62.48,Math.toRadians(-90)));

        robot.setGrasperPos(robot.getGRASPER_CLOSED());
        robot.updateGrasperPos();
        robot.setAxlePos(robot.getAXLE_SERVO_DOWN());
        robot.updateAxleServoPos();


        Action moveToSubAction = robot.drive.actionBuilder(robot.drive.pose)

                .strafeToConstantHeading(new Vector2d(12.5, 39.3))
                .build();






        waitForStart();




        Actions.runBlocking(moveToSubAction);

        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_VERTICAL()+300);
        robot.updatePivotMotorPos();

        robot.delay(.1);
        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_VERTICAL());
        robot.delay(1);
        robot.setAxlePos(robot.getAXLE_SERVO_UP());
        robot.updateAxleServoPos();
        robot.delay(2);
        //add delay
        robot.setSlidesPos(robot.getSLIDES_ABOVE_HIGH_RUNG());
        robot.updateSlidesPos();
        robot.delay(2);

        //slides up


        Action moveSlidesOverHighRung = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(12.60, 36), Math.toRadians(-90))
                .build();


        //Actions.runBlocking(moveSlidesOverHighRung);
        robot.delay(.5);
        //----
        robot.setSlidesPos(robot.getSLIDES_PUT_SP_ON_HIGH_RUNG()-30);//clip specimen
        robot.updateSlidesPos();
        robot.delay(2);

        robot.setGrasperPos(robot.getGRASPER_OPEN());//release specimen
        robot.updateGrasperPos();
        robot.delay(2);

        //robot.setSlidesPos(robot.getSLIDES_ABOVE_HIGH_RUNG());//slides back up
        //robot.updateSlidesPos();
        //robot.delay();



        Action moveBackAwayFromSub = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToConstantHeading(new Vector2d(12.00, 41.00))
                .build();

        Actions.runBlocking(moveBackAwayFromSub);

        robot.setSlidesPos(0);//slides down
        robot.updateSlidesPos();
        robot.delay(.2);

        //*note* delete these 3 lines once specimen works
        //robot.setPivotMotorPos(0);//pivot motor back
        //robot.updatePivotMotorPos();
        robot.delay(4);
        //end note

        Action moveToFirstYellowSample = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToConstantHeading(new Vector2d(14, 48))
                .strafeToConstantHeading(new Vector2d(52, 52))//move to first yellow sample
                .build();
        Actions.runBlocking(moveToFirstYellowSample);

        robot.setPivotMotorPos(5221);
        robot.updatePivotMotorPos();
        robot.delay(.2);

        robot.setAxlePos(robot.getAXLE_SERVO_UP());
        robot.updateAxleServoPos();
        robot.delay(6);

        robot.setGrasperPos(robot.getGRASPER_CLOSED());
        robot.updateGrasperPos();
        robot.delay(.5);

        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_VERTICAL());
        robot.updatePivotMotorPos();
        robot.delay(.5);

        Action moveToHighBucket1 = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(48, 45), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(56, 56), Math.toRadians(-315))
                .build();
        Actions.runBlocking(moveToHighBucket1);

        robot.setSlidesPos(robot.getSLIDES_MAX());
        robot.updateSlidesPos();


        //move forward?
        robot.delay(1);
        robot.setGrasperPos(robot.getGRASPER_OPEN());
        robot.updateGrasperPos();
        robot.delay(.2);

        Action moveBackFromHB = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToConstantHeading(new Vector2d(54, 54))
                .build();
        Actions.runBlocking(moveBackFromHB);//move back from net zone a bit
        robot.delay(.2);

        robot.setSlidesPos(0);
        robot.updateSlidesPos();
        robot.delay(1);

        Action moveToSecondYellowSample = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(54, 56), Math.toRadians(-90))
                .strafeToLinearHeading(new Vector2d(56, 45), Math.toRadians(-90))
                .build();
        Actions.runBlocking(moveToSecondYellowSample);

        //grab second sample
        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_HORIZONTAL());
        robot.updatePivotMotorPos();
        robot.delay(3);

        robot.setGrasperPos(robot.getGRASPER_CLOSED());
        robot.updateGrasperPos();
        robot.delay(.4);

        robot.setPivotMotorPos(robot.getPIVOT_MOTOR_VERTICAL());
        robot.updatePivotMotorPos();
        robot.delay(2);


        Action moveToHighBucket2 = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToLinearHeading(new Vector2d(59.5, 45), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(56, 56), Math.toRadians(-315))
                .build();
        Actions.runBlocking(moveToHighBucket2);//bring yellow sample to HB

        robot.setSlidesPos(robot.getSLIDES_MAX());
        robot.updateSlidesPos();
        //move forward?
        robot.delay(3);
        robot.setGrasperPos(robot.getGRASPER_OPEN());
        robot.updateGrasperPos();
        robot.delay(.2);

        Action moveBackFromHB2 = robot.drive.actionBuilder(robot.drive.pose)
                .strafeToConstantHeading(new Vector2d(54, 54))
                .build();
        Actions.runBlocking(moveBackFromHB);//move back from net zone a bit
        robot.delay(.2);





        robot.resetAll();
        //servos
        robot.updateAxleServoPos();
        robot.updateGimbalPos();
        robot.updateGrasperPos();
        //motors
        robot.updatePivotMotorPos();
        robot.updateSlidesPos();
        robot.delay(5);

    }
}

