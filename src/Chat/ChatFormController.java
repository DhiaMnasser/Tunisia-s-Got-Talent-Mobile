/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;

import com.codename1.capture.Capture;
import com.codename1.components.ToastBar;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.rad.controllers.Controller;
import com.codename1.rad.controllers.FormController;
import com.codename1.rad.models.Entity;
import com.codename1.rad.models.EntityList;
import com.codename1.rad.nodes.ActionNode;
import com.codename1.rad.nodes.ViewNode;
import com.codename1.rad.schemas.ChatRoom;
import static com.codename1.rad.ui.UI.action;
import static com.codename1.rad.ui.UI.actions;
import static com.codename1.rad.ui.UI.icon;
import com.codename1.rad.ui.chatroom.ChatBubbleView;
import com.codename1.rad.ui.chatroom.ChatRoomView;
import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 *
 * @author frauDEee
 */
public class ChatFormController extends FormController {
public static final ActionNode send = action(icon(FontImage.MATERIAL_SEND));
public static final ActionNode capturePhoto = action(icon(FontImage.MATERIAL_CAMERA));
        

    public ChatFormController(Controller parent) {
        super(parent);
        Form f = new Form("TGT Chat room", new BorderLayout());
        ViewNode viewNode = new ViewNode(
            actions(ChatRoomView.SEND_ACTION, send),
        actions(ChatRoomView.TEXT_ACTIONS, capturePhoto)
        );
            
        ChatRoomView view = new ChatRoomView(createViewModel(), viewNode, f);
        f.add(CENTER, view);
        setView(f);
        addActionListener(send, evt->{
    evt.consume();
    ChatRoomView.ViewModel room = (ChatRoomView.ViewModel)evt.getEntity();
    String textFieldContents = room.getInputBuffer();
    if (textFieldContents != null && !textFieldContents.isEmpty()) {
        ChatBubbleView.ViewModel message = new ChatBubbleView.ViewModel();
        message.messageText(textFieldContents);
        message.date(new Date());
        message.isOwn(true); // Indicates that this is sent by "this" user
                            // so bubble is on right side of room view.

        // Now add the message
        room.addMessages(message);

        // Clear the text field contents
        room.inputBuffer("");
    }

} 
        
        );
        
        addActionListener(capturePhoto, evt->{
    evt.consume();
    String photoPath = Capture.capturePhoto();
    if (photoPath == null) {
        // User canceled the photo capture
        return;
    }


    File photos = new File("photos"); 
    photos.mkdirs();
    Entity entity = evt.getEntity();
    File photo = new File(photos, System.currentTimeMillis()+".png");
    try (InputStream input = FileSystemStorage.getInstance().openInputStream(photoPath);
            OutputStream output = FileSystemStorage.getInstance().openOutputStream(photo.getAbsolutePath())) {
        Util.copy(input, output);

        ChatBubbleView.ViewModel message = new ChatBubbleView.ViewModel();
        message.attachmentImageUrl(photo.getAbsolutePath()); 
        message.isOwn(true);
        message.date(new Date());
        EntityList messages = entity.getEntityList(ChatRoom.messages); 
        if (messages == null) {
            throw new IllegalStateException("This chat room has no messages list set up");
        }
        messages.add(message); 

    } catch (IOException ex) {
        Log.e(ex);
        ToastBar.showErrorMessage(ex.getMessage());
    }
});
    }

  

    /**
     * Creates a view model for the chat room.
     * @return
     */
    private Entity createViewModel() {
        ChatRoomView.ViewModel room = new ChatRoomView.ViewModel();

        ChatBubbleView.ViewModel message = new ChatBubbleView.ViewModel();
        message.messageText("Hello World");
        room.addMessages(message);
        return room;
    }

}
