package Steps.Objects.Emails;

import Steps.ObjectProject;
import Steps.ObjectUser;

import java.util.ArrayList;

import static Pages.Emails.EMAIL_RESET_PASSWORD_LINK;
import static Pages.UrlStrings.*;

final public class ReferenceEmail {
    private AbstractEmail abstractEmail;
    public AbstractEmail getAbstractEmail() {
        return abstractEmail;
    }
    public static String reportSchedule;
    public static String reportName;
    public static ObjectProject projectInEmail;
    public static ObjectUser invitedUser;
    public static ObjectUser inviterUser;
    public static ArrayList<ObjectUser> usersArrayList;
    public static String followerEmailOrTeamNameSurname;
    public static String thisEmailSubject;



    public ReferenceEmail buildEmail (EmailTypes emailType, ObjectUser user)  {
        new ReferenceEmail();
        switch (emailType) {
            case DEFAULT:
                //Depends on host selection Pekama or Community in tests
                this.abstractEmail = AbstractEmail.builder()
                        .emailSubject("")
                        .emailTitle("")
                        .emailText("")
                        .emailButtonLinkText("")
                        .emailButtonText("")
                        .emailLinkConfirmRegistrationInButton("")
                        .emailLinkConfirmRegistration("")
                        .build();
                break;
            case MESSAGE_EMAIL:
                //Emails from messages
                if(user.isSignUpSucceed == false){
                    followerEmailOrTeamNameSurname = user.email;
                }
                if(user.isSignUpSucceed == true){
                    followerEmailOrTeamNameSurname = user.nameSurname;
                }
                System.out.println(thisEmailSubject);
                this.abstractEmail = AbstractEmail.builder()
                        .emailSubject(thisEmailSubject)
                        .emailTitle("A <strong>Pekama Conversation</strong> between <strong>"+inviterUser.nameSurname+"</strong>, <strong>"+followerEmailOrTeamNameSurname+"</strong>.")
                        .emailText("Lorem ipsum dolor sit amet")
                        .emailButtonLinkText("Reply in Pekama")
                        .emailLinkBackToPekama("sendgrid.net/wf/click?")
                        .build();
                break;
            case REPORT:
                //Pekama only
                this.abstractEmail = AbstractEmail.builder()
                        .emailSubject("Pekama Report \""+reportName+"\" - ")
                        .emailText("This is the report that you configured in Pekama. You will get it every "+reportSchedule+" days.")
                        .emailLinkBackToPekama(REPORT_BACK_LINK)
                        .emailLinkUnSubscribe(REPORT_UNSUBSCRIBE_LINK)
                        .build();
                break;
            case SIGN_UP:
                //Depends on host selection Pekama or Community in tests
                this.abstractEmail = AbstractEmail.builder()
                        .emailSubject("Welcome to Pekama! Just one more click")
                        .emailTitle("Almost there...")
                        .emailText("To finish registration, please confirm your account.")
                        .emailButtonLinkText("Complete my registration")
                        .emailButtonText("Complete my registration")
                        .emailLinkConfirmRegistrationInButton(EMAIL_CONFIRM_REGISTRATION_LINK)
                        .emailLinkConfirmRegistration(EMAIL_CONFIRM_REGISTRATION_LINK)
                        .emailLinkMailTo(user.email)
                        .build();
                break;
            case RESET_PASSWORD:
                //Depends on host selection Pekama or Community in tests
                this.abstractEmail = AbstractEmail.builder()
                        .emailSubject("Password Restoration [Pekama]")
                        .emailTitle("Password Restoration")
                        .emailText("You've received this e-mail because you requested to reset the password for your user account. Press the button bellow to complete restoration.")
                        .emailButtonLinkText("Reset Password")
                        .emailButtonText("Reset Password")
                        .emailLinkResetPasswordInButton(EMAIL_RESET_PASSWORD_LINK)
                        .emailLinkResetPassword(EMAIL_RESET_PASSWORD_LINK)
                        .build();
                break;
            case INVITE_IN_PROJECT:
                //Pekama only - invite collaborator
                this.abstractEmail = AbstractEmail.builder()
                        .emailSubject(this.inviterUser.nameSurname+" invited you to")
                        .emailTitle(this.inviterUser.nameSurname+" invited you to collaborate")
                        .emailText(this.inviterUser.nameSurname+" from  created a project for "+this.projectInEmail.projectName.toUpperCase()+" and invites you to join in.")
                        .emailButtonLinkText("Join Project "+this.projectInEmail.projectName.toUpperCase())
                        .emailLinkPathPart1(EMAIL_INVITE_IN_PEKAMA_LINK1)
                        .emailLinkPathPart2(EMAIL_INVITE_IN_PROJECT_LINK2)
                        .emailLinkMailTo(this.invitedUser.email)
                        .build();
                break;
        }
        return this;
    }
}
