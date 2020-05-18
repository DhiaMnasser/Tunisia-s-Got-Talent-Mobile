/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

/**
 *
 * @author frauDEee
 */



import com.codename1.rad.controllers.ApplicationController;
import com.codename1.rad.controllers.ControllerEvent;

public class ChatController extends ApplicationController {

    public ChatController() {
        new ChatFormController(this).getView().show();
    }
     @Override
    public void actionPerformed(ControllerEvent evt) {
       if (evt instanceof StartEvent) {
            evt.consume();
            new ChatFormController(this).getView().show();
        }
    }

}
