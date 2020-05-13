package com.mycompany.myapp.gui.gth;


import com.codename1.capture.Capture;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.gthcompagny.myapp.entities.gth.Upload;
import com.mycompany.myapp.services.gth.ServiceUpload;
import java.io.IOException;
import rest.file.uploader.tn.FileUploader;


public class AddPubForm extends Form {
    Label lbNomVi=new Label("Votre video");

    private FileUploader file;
    String fileNameInServer;
    private String videoPath;
    Upload up;
    
    
    

    public AddPubForm(Form previous){


        Container cnt=new Container(BoxLayout.y());
        Container cntVi=new Container(BoxLayout.x());
        setLayout(new BorderLayout());
        setTitle("Ajouter votre Video");
        getToolbar().addCommandToSideMenu("Liste des Prestations",null,ev->{
            new ListPubForm(previous).show();
        });
        getToolbar().addCommandToSideMenu("Ajouter une Prestation",null,ev->{
            this.show();
        });

        TextField tfTitre=new TextField();
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
                
                FileUploader fu = new FileUploader("http://localhost:81/worshop/web");
                
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
                up=new Upload(tfTitre.getText(), "gth","Carte", taDesc.getText(),"file:/C:/wamp64/www/worshop/web/uploads/"+fileNameInServer);
                ServiceUpload.getInstance().addUpload(up);
                Dialog.show("Success", "Bien Enrégistré","Ok",null);
                new ListPubForm(previous).show();
            }else{
                Dialog.show("Error", "Veuillez bien entrer vos infos","Ok",null);
            }
        });
        cntVi.addAll(btnVideo,lbNomVi);
        cnt.addAll(tfTitre,taDesc,cntVi);
        add(BorderLayout.CENTER,cnt);
        add(BorderLayout.SOUTH,btnValider);





    }
}
