package org.firstinspires.ftc.teamcode;

//import com.google.blocks.ftcrobotcontroller.runtime.CRServoAccess;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class CompTeleop extends LinearOpMode {

    private com.qualcomm.robotcore.hardware.HardwareMap HardwareMap;
    BaseRobot robot;



    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive d = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        robot = new BaseRobot(hardwareMap);



        waitForStart();

        while(!isStopRequested() && opModeIsActive()){


            robot.drive.setDrivePowers(new PoseVelocity2d(new Vector2d(-gamepad1.left_stick_y, -gamepad1.left_stick_x), gamepad1.right_stick_x));


            //updates
            robot.updateGimbalPos();
            robot.updateHangArmPos();
            robot.updatePivotMotorPos();
            robot.updateSlidesPos();
            //end


            //gimbal servo

            //end gimbal servo


            //slides
            robot.changeSlidesPos((int)(-gamepad2.right_stick_y * 10));
            robot.changePivotMotorPos((int) (-gamepad2.left_stick_y * 10));
            robot.changeHangArmPos((int) ((gamepad2.right_trigger-gamepad2.left_trigger) * 10));
            if(gamepad2.dpad_down){
                robot.slidesReset();
            }
            if(gamepad1.left_bumper){
                robot.changeGimbalPos(-.1);
            }
            if(gamepad1.right_bumper){
                robot.changeGimbalPos(.1);
            }

            if(gamepad2.a){
                robot.axleRotation.setPosition(.6);
            }
            if(gamepad2.b){
                robot.axleRotation.setPosition(0);
            }
            if(gamepad2.y){
                robot.axleRotation.setPosition(.4);
            }
            if(gamepad2.x){
                robot.axleRotation.setPosition(.8);
            }



            //end slides
            //_____________________________________________________________________________________


            if (gamepad1.dpad_left){
                robot.reachToSub();
            }

            //if (gamepad1.dpad_up){
              //  robot.basketScoring();
            //}
            //if (gamepad1.y){
              //  robot.specimenScoring();
            //}


            //telemetry
            //_____________________________________________________________________________________

            /*telemetry.addData("left slides position: ", leftSliderPos);
            telemetry.addData("hang arm position: ", robot.hangArmPos);
            telemetry.addData("right slides position: ", rightSliderPos);
            telemetry.addData("pivot motor position: ", pivotMotorPos);
            telemetry.addData("Slides motor delta: ", slidesMotorDelta);
            */
            telemetry.update();
            //end telemetry
            //_____________________________________________________________________________________
        }
    }
}