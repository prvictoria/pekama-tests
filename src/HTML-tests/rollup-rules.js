//vars = require('./JsonDraft.json');
// const vars = require('./00_VAR.json');
// console.log(vars[0].userGmail06);

const passwGeneric ='asui67we34';
const loginUser01 ='testqweeco001@gmail.com';
const loginUser02 ='testqweeco002@gmail.com';
const loginUser03 ='testqweeco003@gmail.com';
var loginField = 'name=username';
var passwordField = 'name=password';
var buttonLogin = 'css=button.btn.btn-default';
var personalSettingsCurrentPassword = 'name=original_password';
var personalSettingsNewPassword = 'name=password1';
var personalSettingsConfirmPassword = 'name=password2';
var window_id = '_blank';
var deleteProjectButton = '//i[@class="icon pkm-icon-trash"]';
var allTeamsCheckbox = "//*[@ng-model='uiState.allTeams']";
var bulkAllCheckbox = "//*[@ng-click='selectAll()']";
var spinnerPage = ".//*[@id='progress-indicator']/span";

var modal_body = 'css=.modal-content';
var modal_title = '//*[@class="modal-title ng-binding"]';
var button_YES = 'css=div.modal-footer.ng-scope > button.btn.btn-primary';
var button_ADDCONTACT = "//button[@type='button'][contains(.,'Add contact')]";
var modal_title_simple = '//h3';
var CookieButtonPresent;

var manager = new RollupManager();
manager.addRollupRule({
    name : 'login as USER01 - testqweeco001@gmail.com',
    description : 'short login',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];

        commands.push({
            command : 'setSpeed',
            target : '50'
        });
        commands.push({
            command : 'open',
            target : 'accounts/login/'
        });
        commands.push({
            command : 'type',
            target : loginField,
            value: loginUser01
        });
        commands.push({
            command : 'type',
            target : passwordField,
            value: passwGeneric
        });
        commands.push({
            command : 'clickAndWait',
            target : buttonLogin
        });
        return commands;
    }
});
manager.addRollupRule({
    name : 'login as USER02 - testqweeco002@gmail.com',
    description : 'short login',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];

        commands.push({
            command : 'setSpeed',
            target : '50'
        });
        commands.push({
            command : 'open',
            target : 'accounts/login/'
        });
        commands.push({
            command : 'type',
            target : loginField,
            value: loginUser02
        });
        commands.push({
            command : 'type',
            target : passwordField,
            value: passwGeneric
        });
        commands.push({
            command : 'clickAndWait',
            target : buttonLogin
        });
        return commands;
    }
});
manager.addRollupRule({
    name : 'login as USER03 - testqweeco003@gmail.com',
    description : 'short login',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];

        commands.push({
            command : 'setSpeed',
            target : '50'
        });
        commands.push({
            command : 'open',
            target : 'accounts/login/'
        });
        commands.push({
            command : 'type',
            target : loginField,
            value: loginUser03
        });
        commands.push({
            command : 'type',
            target : passwordField,
            value: passwGeneric
        });
        commands.push({
            command : 'clickAndWait',
            target : buttonLogin
        });
        return commands;
    }
});
manager.addRollupRule({
    name : 'Click ADD contact and wait for MW',
    description : 'Call New Contact Modal',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];
        commands.push({
            command : 'setSpeed',
            target : '50'
        });
        commands.push({
            command : 'click',
            target : button_ADDCONTACT
        });
        commands.push({
            command : 'waitForElementPresent',
            target : modal_title_simple
        });
        commands.push({
            command : 'verifyText',
            target : modal_title_simple,
            value: 'Contact'
        });
        commands.push({
            command : 'setSpeed',
            target : '10'
        });
        return commands;
    }
});

manager.addRollupRule({
    name : 'modalConfirmAction',
    description : 'confirm YES',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];
        commands.push({
            command : 'setSpeed',
            target : '50'
        });
        commands.push({
            command : 'waitForElementPresent',
            target : modal_body
        });
        commands.push({
            command : 'verifyText',
            target : modal_title,
            value: 'Confirm action'
        });
        commands.push({
            command : 'verifyText',
            target : '//*[@class="modal-body ng-scope"]',
            value: 'Are you sure?'
        });
        commands.push({
            command : 'verifyText',
            target : button_YES,
            value: 'Yes'
        });
        commands.push({
            command : 'click',
            target : button_YES
        });
        commands.push({
            command : 'waitForElementNotPresent',
            target : modal_body
        });
        return commands;
    }
});
manager.addRollupRule({
    name : 'waitForPageSpinner not Present',
    description : 'wait if page spinner present',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];
        commands.push({
            command : 'pause',
            target : '1000'
        });
        commands.push({
            command : 'waitForElementNotPresent',
            target : spinnerPage
        });
        commands.push({
            command : 'pause',
            target : '750'
        });
        return commands;
    }
});
manager.addRollupRule({
    name : 'Delete project via button [DELETE}',
    description : 'Delete Project and confirm YES',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];
        commands.push({
            command : 'setSpeed',
            target : '50'
        });

        commands.push({
            command : 'waitForElementPresent',
            target : deleteProjectButton
        });
        commands.push({
            command : 'click',
            target : deleteProjectButton
        });

        commands.push({
            command : 'waitForElementPresent',
            target : modal_body
        });
        commands.push({
            command : 'verifyText',
            target : modal_title,
            value: 'Confirm action'
        });
        commands.push({
            command : 'verifyText',
            target : '//*[@class="modal-body ng-scope"]',
            value: 'Are you sure?'
        });
        commands.push({
            command : 'verifyText',
            target : button_YES,
            value: 'Yes'
        });
        commands.push({
            command : 'click',
            target : button_YES
        });
        commands.push({
            command : 'waitForElementNotPresent',
            target : modal_body
        });
        return commands;
    }
});
manager.addRollupRule({
    name : 'select set All Team checkbox',
    description : 'set All permission',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];

        commands.push({
            command : 'setSpeed',
            target : '50'
        });
        commands.push({
            command : 'waitForValue',
            target : allTeamsCheckbox,
            value: 'off'
        });
        commands.push({
            command : 'click',
            target : allTeamsCheckbox
        });
        commands.push({
            command : 'waitForValue',
            target : allTeamsCheckbox,
            value: 'on'
        });
        return commands;
    }
});
manager.addRollupRule({
    name : 'select unset All Team checkbox',
    description : 'UNset All permission',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];

        commands.push({
            command : 'setSpeed',
            target : '50'
        });
        commands.push({
            command : 'waitForValue',
            target : allTeamsCheckbox,
            value: 'on'
        });
        commands.push({
            command : 'click',
            target : allTeamsCheckbox
        });
        commands.push({
            command : 'waitForValue',
            target : allTeamsCheckbox,
            value: 'off'
        });
        return commands;
    }
});
/*manager.addRollupRule({
    name : 'bulk action - select All checkbox',
    description : 'select all items',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];

        commands.push({
            command : 'setSpeed',
            target : '50'
        });
        commands.push({
            command : 'waitForValue',
            target : bulkAllCheckbox,
            value: 'off'
        });
        commands.push({
            command : 'click',
            target : bulkAllCheckbox
        });
        commands.push({
            command : 'waitForValue',
            target : bulkAllCheckbox,
            value: 'on'
        });
        return commands;
    }
});
manager.addRollupRule({
    name : 'bulk action - deselect All checkbox',
    description : 'un-select all items',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];

        commands.push({
            command : 'setSpeed',
            target : '50'
        });
        commands.push({
            command : 'waitForValue',
            target : bulkAllCheckbox,
            value: 'on'
        });
        commands.push({
            command : 'click',
            target : bulkAllCheckbox
        });
        commands.push({
            command : 'waitForValue',
            target : bulkAllCheckbox,
            value: 'off'
        });
        return commands;
    }
});*/

manager.addRollupRule({
    name : 'verify value Personal settings',
    description : 'password fields',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];
        commands.push({
            command : 'refreshAndWait'
        });
        commands.push({
            command : 'waitForElementPresent',
            target : "link=Login & Security"
        });
        commands.push({
            command : 'click',
            target : "link=Login & Security"
        });
        commands.push({
            command : 'verifyValue',
            target : personalSettingsCurrentPassword
        });
        commands.push({
            command : 'verifyValue',
            target : personalSettingsNewPassword
        });
        commands.push({
            command : 'verifyValue',
            target : personalSettingsConfirmPassword
        });
        return commands;
    }
});

manager.addRollupRule({
    name : 'Login Gmail as...User03',
    description : 'Login Gmail as...User03',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];
        commands.push({
            command : 'setSpeed',
            target : '100'
        });
        commands.push({
            command : 'open',
            target : "https://mail.google.com/mail/"
        });
        commands.push({
            command : 'waitForElementPresent',
            target : "name=Email"
        });
        commands.push({
            command : 'type',
            target : "name=Email",
            value: "zzkarlson@gmail.com"
        });
        commands.push({
            command : 'click',
            target : "name=signIn"
        });
        commands.push({
            command : 'waitForElementPresent',
            target : "name=Passwd"
        });
        commands.push({
            command : 'type',
            target : "id=Passwd",
            value: "ergseg845jsdfli256sdfhsd1"
        });
 //       commands.push({
 //           command : 'waitForValue',
 //           target : "//input[@id='PersistentCookie']",
 //           value: "on"
 //       });
 //       commands.push({
 //           command : 'click',
 //           target : "//input[@id='PersistentCookie']"
 //       });
 //       commands.push({
 //           command : 'waitForValue',
 //           target : "//input[@id='PersistentCookie']",
 //           value: "off"
 //       });
        commands.push({
            command : 'click',
            target : "name=signIn"
       });
        return commands;
    }
});
manager.addRollupRule({
    name : 'LogOut Gmail as...User03',
    description : 'LogOut Gmail as...User03',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];
        commands.push({
            command : 'setSpeed',
            target : '100'
        });
        commands.push({
            command : 'waitForElementPresent',
            target : "//div/a/span"
        });
        commands.push({
            command : 'click',
            target : "//div/a/span"
        });
        commands.push({
            command : 'waitForElementPresent',
            target : "id=gb_71"
        });
        commands.push({
            command : 'click',
            target : "id=gb_71"
        });
        commands.push({
            command : 'waitForText',
            target : "css=h1",
            value: "One account. All of Google."
        });
        commands.push({
            command : 'verifyValue',
            target : "//input[@id='Email']"
        });
        return commands;
    }
});

manager.addRollupRule({
    name : 'Submitt cookie if displayed',
    description : 'Click OK if need',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];
        commands.push({
            command : 'open',
            target : '/'
        });
        commands.push({
            command : 'waitForElementPresent',
            target : 'link=log in'
        });
        commands.push({
            command : 'verifyElementPresent',
            target : 'link=log in'
        });
        //Check that BUTTON present
        commands.push({
            command: 'pause',
            target: '500'
         });
        commands.push({
             command: 'storeElementPresent',
             target: 'link=Got it!',
             value: 'CookiePresent'
         });
        commands.push({
            command: 'echo',
            target: 'CookiePresent'
         });
        commands.push({
            command : 'setSpeed',
            target : '50'
         });
        if ("CookiePresent" == 'true' )
            //store _blank target
             commands.push({
                 command: 'storeAttribute',
                 target: 'link=Got it!@target',
                 value: window_id
             });
             //Close blank window
             commands.push({
                 command: 'click',
                 target: 'link=Got it!'
             });
             commands.push({
                 command: 'selectWindow',
                 target: window_id
             });

             commands.push({
                 command: 'close'
             });
             //Check that login page present
             commands.push({
                 command: 'waitForElementPresent',
                 target: 'link=log in'
             });
             commands.push({
                 command: 'verifyElementPresent',
                 target: 'link=log in'
             });
             return commands;
        }

});
manager.addRollupRule({
    name : 'Click Cookie and submit',
    description : 'Confirm cookie',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];
        commands.push({
            command: 'storeAttribute',
            target: 'link=Got it!@target',
            value: window_id
            });
        //Close blank window
        commands.push({
            command: 'click',
            target: 'link=Got it!'
        });
        commands.push({
            command: 'selectWindow',
            target: window_id
        });
        commands.push({
            command: 'close'
        });
        //Check that login page present
        commands.push({
            command: 'waitForElementPresent',
            target: 'link=log in'
        });
        commands.push({
            command: 'verifyElementPresent',
            target: 'link=log in'
        });
        return commands;
    }

});
manager.addRollupRule({
    name : 'Box Auth - user06',
    description : 'select pop-up and fill credentials',
    args : [],
    commandMatchers : [],
    getExpandedCommands : function(args) {
        var commands = [];
        commands.push({
            command : 'open',
            target : '${urlDashboard}'
        });
        // commands.push({
        //     command : 'waitForElementPresent',
        //     target : 'link=log in'
        // });
        // commands.push({
        //     command : 'verifyElementPresent',
        //     target : 'link=log in'
        // });

        return commands;


    }
});

// javascript{var d = new Date (); d.setDate ( d.getDate() + 0 ); ( d.getFullYear() + "-" + (d.getMonth() + 0) + "-" + d.getDate() );}
// Current Time - javascript{(new Date().getHours()+" : " + new Date().getMinutes() + " : " + new Date().getSeconds())}
// Current year - javascript{(new Date()).getFullYear()}
// Current Month - javascript{(new Date().getMonth()) + 1}
// Current date - javascript{(new Date().getDate().toString())}