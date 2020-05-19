package com.mycompany.myapp.gui.gth;


import com.codename1.capture.Capture;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.*;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.gthcompagny.myapp.entities.gth.Upload;
import com.mycompany.myapp.gui.mohamed.BaseForm;
import com.mycompany.myapp.gui.mohamed.NewsfeedForm;
import com.mycompany.myapp.services.gth.ServiceUpload;
import com.mycompany.myapp.services.mohamed.UserCourant;
import java.io.IOException;
import rest.file.uploader.tn.FileUploader;


public class AddPubForm extends BaseForm {
    Label lbNomVi=new Label("Votre video");

    private FileUploader file;
    String fileNameInServer;
    private String videoPath;
    Upload up;
    
    
    

    public AddPubForm(Resources res){
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);


        Container cnt=new Container(BoxLayout.y());
        setUIID("SignIn");
        Container cntVi=new Container(BoxLayout.x());
        //entete
        super.addSideMenu(res);
        getTitleArea().setUIID("Container");
        setTitle("Welcome-Bienvenue");
        getContentPane().setScrollVisible(false);
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "gjghjh", "", "");
        //addSideMenu(res);
        //entete
        setLayout(new BorderLayout());
        setTitle("Ajouter votre Video");
        
        getToolbar().addCommandToLeftBar("Back",null,ev->{new NewsfeedForm(res).show();});

        TextArea tfTitre=new TextArea();
        tfTitre.setHint("Titre");
        TextArea taDesc=new TextArea();
        taDesc.setHint("Description");
        Button btnValider=new Button("Enregistrer");
        
        Button btnVideo = new Button("Parcourir");
        btnVideo.setMaterialIcon(FontImage.MATERIAL_CLOUD_UPLOAD);
        btnVideo.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                videoPath = Capture.captureVideo();
                
                System.out.println(videoPath);
                String link = videoPath.toString();
                int pod = link.indexOf("/",2);
                String news = link.substring(pod + 2,link.length());
                
                System.out.println(""+news);
                
                FileUploader fu = new FileUploader("http://localhost:81/TGTOf/web");
                
                fileNameInServer = fu.upload(news);
                lbNomVi.setText(fileNameInServer);
                
                }catch(IOException ex){
                    ex.printStackTrace();
                } catch (Exception e) {
                    
                }
            }
        });
        
        btnValider.addActionListener(e->{
            if(!(tfTitre.getText().length()==0 || taDesc.getText().length()==0 || lbNomVi.getText().length()==0 || fileNameInServer.length()==0 )){
                up=new Upload(tfTitre.getText(), UserCourant.ok.getUsername(),"Carte", taDesc.getText(),"file:/C:/wamp64/www/TGTOf/web/uploads/"+fileNameInServer);
                ServiceUpload.getInstance().addUpload(up);
                Dialog.show("Success", "Bien Enrégistré","Ok",null);
                new NewsfeedForm(res).show();
            }else{
                Dialog.show("Error", "Veuillez bien entrer vos infos","Ok",null);
            }
        });
        cntVi.addAll(btnVideo,lbNomVi);
        cnt.addAll(tfTitre,taDesc,cntVi);
       
        //tgt300.png
        add(BorderLayout.NORTH, new Label(res.getImage("tgt300.png"), "LogoLabel"));
        add(BorderLayout.CENTER,cnt);
        add(BorderLayout.SOUTH,btnValider);





    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
    
    

    
}
