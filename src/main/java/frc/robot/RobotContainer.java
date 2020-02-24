/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.TurnToAngleCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
    // The robot's subsystems and commands are defined here...
    private final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();

    private final ExampleCommand autonomousCommand = new ExampleCommand(exampleSubsystem);

    private final DriveSubsystem m_robotDrive = new DriveSubsystem();

    Joystick joy1 = new Joystick(Constants.joy1_id);

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer()
    {
        // Configure the button bindings
        configureButtonBindings();

        m_robotDrive.setDefaultCommand(
                //This is an inline command you can also make a whole new command if wanted
                new RunCommand(() -> m_robotDrive.aDrive(joy1.getRawAxis(1)* -1, joy1.getRawAxis(2))));
    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton JoystickButton}.
     */
    private void configureButtonBindings()
    {
        //This is another inline command that calls setMaxOutput when LB is pressed
        new JoystickButton(joy1, 5)
                .whenPressed(()-> m_robotDrive.setMaxOutput(0.5))
                .whenReleased(() -> m_robotDrive.setMaxOutput(1));


        //Drive Straight while RB is pressed
        new JoystickButton(joy1, 6).whenHeld(new PIDCommand(
                new PIDController(Constants.kStabilizationP, Constants.kStabilizationI,
                        Constants.kStabilizationD),
                // Close the loop on the turn rate
                m_robotDrive::getTurnRate,
                // Setpoint is 0
                0,
                // Pipe the output to the turning controls
                output -> m_robotDrive.aDrive(joy1.getRawAxis(1)* -1, output),
                // Require the robot drive
                m_robotDrive));

        // Turn to 90 degrees when the 'X' button is pressed, with a 5 second timeout
        new JoystickButton(joy1,1)
                .whenPressed(new TurnToAngleCommand(90, m_robotDrive).withTimeout(5));

        // Turn to -90 degrees with a profile when the 'A' button is pressed, with a 5 second timeout
        new JoystickButton(joy1, 3)
                .whenPressed(new TurnToAngleCommand(-90, m_robotDrive).withTimeout(5));
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        // An ExampleCommand will run in autonomous
        return autonomousCommand;
    }
}
