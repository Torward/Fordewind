import Model.UploadFileMsg;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class CSfirstScreen  implements Initializable {
    public Button download_btn;
    public Button upload_btn;
    public TextArea log_area;
    public AnchorPane dragAndDropPane;
    public TilePane serverView;
    public Label text;
    private Pane leftScene;
    private Desktop desktop = Desktop.getDesktop();
    private TilePane tilepane = new TilePane();
    private NettyNetwork network;
    private UploadFileMsg uploadFileMsg = new UploadFileMsg();
    private ClientUploadFileHandler clientUploadFileHandler;
    private String root = "client/clientFiles";
    private String sroot = "server/serverFiles";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tilepane.setPrefTileWidth(70);
        tilepane.setPrefTileHeight(70);
        tilepane.setTileAlignment(Pos.CENTER);
//        File currentDir = new File("server/serverFiles");
//        WatchService watchService = null;
//        while (true) {
//
//        try {
//            watchService = FileSystems.getDefault().newWatchService();
//            Path path = Paths.get("server/serverFiles");
////            displayFileAndDir(currentDir);
//            WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
//            //System.out.format("%s: ", watchKey);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
  //  }





    }


    public void download(ActionEvent actionEvent) {
    }

    public void send(ActionEvent actionEvent) {

    }

    public void fileChoose(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        Stage secondaryStage = new Stage();
        fileChooser.setTitle("Выберете файл для отправки");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        File file = fileChooser.showOpenDialog(secondaryStage);

        if (file != null) {
            sendFile(file);
            List<File> files = Collections.singletonList(file);
            printLog(log_area, files);
        }
    }
    public void fileChooseM(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        Stage secondaryStage = new Stage();
        fileChooser.setTitle("Выберете файл для отправки");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));

        File file = fileChooser.showOpenDialog(secondaryStage);

        if (file != null) {
            sendFile(file);
            List<File> files = Collections.singletonList(file);
            printLog(log_area, files);
        }
    }
// private void displayFileAndDir(File dir){
//        try{
//            ImageView [] a = null;
//            Node img = new Node(new ImageView(new Image(ClassLoader.getSystemResourceAsStream("img/folder.png"),64.0, 64.0, true, true)));
//          File [] list = dir. listFiles();
//          int n = list.length;
//            int pos =0;
//          a = new ImageView[pos];
//            for (int i = 0; i < list.length ; i++) {
//                if(list[i].isDirectory() ){
//                    tilepane.getChildren().clear();
//                   a[pos] =  new TilePane().getChildren().add( img);
//                }else{
//                    long lastModified = file.lastModified();
//                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
//                    tilepane.getChildren().add(Integer.parseInt(file.getName()),new ImageView(String.valueOf(file.getCanonicalFile())));
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
// }

    private void sendFile(File file) {
        try {
            String fileMd5 = file.getName();
            uploadFileMsg.setFilePath(sroot);
            uploadFileMsg.setFile(file);
            uploadFileMsg.setFile_md5(fileMd5);
            uploadFileMsg.setStart(0);

            network = new NettyNetwork(uploadFileMsg);
            //this.desktop.open(file); //Здесь код отправки на серевер или поменяю на send
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendDAnaD(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    public void dropped(DragEvent dragEvent) throws FileNotFoundException {
        List<File>files = dragEvent.getDragboard().getFiles();
        File file = files.get(0);
        if (file != null) {
            sendFile(file);
             files = Collections.singletonList(file);
            printLog(log_area, files);
        }

    }

    private void printLog(TextArea textArea, List<File> files) {
        if (files == null || files.isEmpty()) {
            return;
        }
        for (File file : files) {
            textArea.appendText(file.getAbsolutePath() + "\n");
        }
    }
    public void hover(MouseEvent mouseEvent) {
        dragAndDropPane.setStyle("-fx-background-color: #F2F4F2;");
        text.setStyle("-fx-background-color: #F2F4F2;");

    }
    public void hoverBTN(MouseEvent mouseEvent) {
        download_btn.setStyle("-fx-background-color: #6abcd0;");

    }

    public void objectFree(MouseEvent mouseEvent) {
        dragAndDropPane.setStyle("-fx-background-color: #FFFFFF;");
        text.setStyle("-fx-background-color:#FFFFFF;");

    }
    public void BTNFree(MouseEvent mouseEvent) {
        download_btn.setStyle("-fx-background-color: #4b78bb;");

    }

    public void hoverSendBTN(MouseEvent mouseEvent) {
        upload_btn.setStyle("-fx-background-color: #6abcd0;");
        //upload_btn.setStyle( "-fx-effect: dropshadow(three-pass-box, rgba(106, 188, 208, 0.8), 10, 0, 0, 0);");

    }

    public void sendBTNFree(MouseEvent mouseEvent) {
        upload_btn.setStyle("-fx-background-color: #4b78bb;");
        //upload_btn.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(75, 120, 187, 0.8), 10, 0, 0, 0);");
    }
}
