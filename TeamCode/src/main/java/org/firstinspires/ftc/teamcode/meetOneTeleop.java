package org.firstinspires.ftc.teamcode;

//import com.google.blocks.ftcrobotcontroller.runtime.CRServoAccess;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class meetOneTeleop extends LinearOpMode {

    private com.qualcomm.robotcore.hardware.HardwareMap HardwareMap;
    BaseRobot robot;



    @Override
    public void runOpMode() throws InterruptedException {




        waitForStart();

        MecanumDrive d = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        robot = new BaseRobot(hardwareMap);

        while(!isStopRequested() && opModeIsActive()){


            robot.drive.setDrivePowers(new PoseVelocity2d(new Vector2d(-gamepad1.left_stick_y, -gamepad1.left_stick_x), -gamepad1.right_stick_x));


            //updates
            robot.updateGimbalPos();
            robot.updateHangArmPos();
            robot.updatePivotMotorPos();
            robot.updateSlidesPos();
            robot.updateGrasperPos();
            robot.updateAxleServoPos();
            //end




            //slides
            robot.changeSlidesPos((int)(-gamepad2.right_stick_y * 10));
            robot.changeSlidesPos((int)(gamepad1.right_trigger - gamepad1.left_trigger) * 10);



            //pivot motor
            if (gamepad1.right_bumper){
                robot.changePivotMotorPos(10);
            }
            if (gamepad1.left_bumper){
                robot.changePivotMotorPos(-10);
            }

            robot.changePivotMotorPos((int) (gamepad2.left_stick_y * 10));
            robot.changeHangArmPos((int) ((gamepad2.right_trigger-gamepad2.left_trigger) * 10));


            if(gamepad1.dpad_right){
                robot.setGrasperPos(robot.GRASPER_WIDE_OPEN);
            }
            if(gamepad1.dpad_left){
                //robot.setGrasperPos(1);
            }
            if(gamepad1.dpad_up){
                //robot.setGrasperPos(0);
            }
            if(gamepad1.dpad_down){
                robot.setGrasperPos(robot.GRASPER_CLOSED);
            }

            if(gamepad2.dpad_down){
                robot.setAxlePos(robot.getAXLE_SERVO_UP());
            }
            if(gamepad2.dpad_up){
                robot.setAxlePos(robot.getAXLE_SERVO_BACK());
            }

            if(gamepad2.left_bumper){
                robot.changeGimbalPos(-.01);
            }
            if(gamepad2.right_bumper){
                robot.changeGimbalPos(.01);
            }
            if(gamepad2.left_bumper && gamepad2.right_bumper){
                robot.setGimbalPos(robot.GIMBAL_RESTING_POS);
            }

            if (gamepad2.a){

                robot.setPivotMotorPos(10);

            }
            if (gamepad2.x){

            }
            if (gamepad2.y){
                robot.setPivotMotorPos(robot.PIVOT_MOTOR_VERTICAL);
            }
            if (gamepad2.b){
                robot.setPivotMotorPos(robot.PIVOT_MOTOR_HORIZONTAL);
            }


            //end slides
            //_____________________________________________________________________________________






            //telemetry
            //_____________________________________________________________________________________

            telemetry.addLine("Motors: ");
            telemetry.addLine();


            telemetry.addLine("Slides: ");
            telemetry.addData("left slides position: ", robot.getLeftSliderPos());
            telemetry.addData("left slide power: ", robot.getLEFT_SLIDE_POWER());
            telemetry.addLine();

            telemetry.addData("right slides position: ", robot.getRightSliderPos());
            telemetry.addData("right slides power: ", robot.getRIGHT_SLIDE_POWER());
            telemetry.addLine();
            telemetry.addLine();

            //telemetry.addData("hang arm position: ", robot.getHangArmPos());

            telemetry.addLine("Pivot motor: ");
            telemetry.addData("pivot motor position: ", robot.getPivotMotorPos());
            telemetry.addData("pivot motor power: ", robot.getPIVOT_MOTOR_POWER());

            telemetry.addLine();
            telemetry.addLine("Servos: ");
            telemetry.addData("gimbal servo pos" , robot.getGimbalPos());
            telemetry.addData("Axle servo pos" , robot.getAxlePos());
            telemetry.addData("grasper pos" , robot.getGrasperPos());

            telemetry.addLine();
            telemetry.addLine();

            telemetry.addLine("controls: ");
            telemetry.addLine("i'll put them in later");

            telemetry.update();
            //end telemetry
            //_____________________________________________________________________________________
        }
    }
}