package application;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

import application.Operaciones;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{


    	DBManager accesoBD = new DBManager();
        Operaciones opCliente = new Operaciones(accesoBD.getConnection());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("bdBoletaje");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label n0 = new Label("Id del Cliente :");
        grid.add(n0, 0, 1);
        Label n1 = new Label("Nombre del nuevo cliente :");
        grid.add(n1, 0, 2);
        Label n2 = new Label("Apellidos:");
        grid.add(n2, 0, 3);
        Label n3 = new Label("Direccion :");
        grid.add(n3, 0, 4);

        TextField TextId = new TextField();
        grid.add(TextId, 1, 1);
        TextField TextNombre = new TextField();
        grid.add(TextNombre, 1, 2);
        TextField TextApe = new TextField();
        grid.add(TextApe, 1, 3);
        TextField TextDi = new TextField();
        grid.add(TextDi, 1, 4);

        //grid.add(table,1,5);

        Button btnI = new Button("Ingresar");
        Button btnM = new Button("Modificar");
        Button btnE = new Button("Eliminar");
        Button btnCI = new Button("Consultar");
        Button btnS = new Button("Salir");


        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnI,btnM,btnE,btnCI,btnS);
        grid.add(hbBtn, 1, 8);
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);



        Scene sc = new Scene(grid, 800, 400);
        primaryStage.setScene(sc);
        primaryStage.show ();

        /*btnI.setDisable(true);
        btnM.setDisable(true);
        btnE.setDisable(true);
        btnC.setDisable(true);
        btnL.setDisable(true);*/





    	//primaryStage.close();

        btnI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String nombre =TextNombre.getText() ;
                String apellidos =TextApe.getText() ;
                String direccion =TextDi.getText() ;
                //int id= Integer.parseInt(TextId.getText());

                if(TextId.getText().trim().isEmpty()){
                	if(TextNombre.getText().trim().isEmpty()
                			||TextApe.getText().trim().isEmpty()
                			||TextDi.getText().trim().isEmpty()){
                	     Alert fail= new Alert(AlertType.INFORMATION);
                	     fail.setHeaderText("Error");
                	     fail.setContentText("Hay uno o mas campos vacios");
                	     fail.showAndWait();
                	    } else {
                	     Alert alert = new Alert(AlertType.INFORMATION);
                	     alert.setHeaderText("Aviso");
                	     alert.setContentText("Exito en agregar nuevo registro");
                	     alert.showAndWait();
                	     opCliente.insertCliente(nombre, apellidos,direccion);
                	     TextNombre.setText("");
                	     TextApe.setText("");
                	     TextDi.setText("");
                	    }

            	    }else{
            	    	Alert fail= new Alert(AlertType.INFORMATION);
               	     fail.setHeaderText("Error");
               	     fail.setContentText("El campo Id no necesita estar lleno para ingresar registros");
               	     fail.showAndWait();
               	     TextId.setText("");
            	    }




            }

        });
        btnE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	int id= Integer.parseInt(TextId.getText());

            	if(TextId.getText().trim().isEmpty()){
            		Alert fail= new Alert(AlertType.INFORMATION);
              	     fail.setHeaderText("Error");
              	     fail.setContentText("El campo Id no necesita estar lleno para ingresar registros");
              	     fail.showAndWait();
            	    }else{
            	    	if(TextNombre.getText().trim().isEmpty()
                    			||TextApe.getText().trim().isEmpty()
                    			||TextDi.getText().trim().isEmpty()){
            	    		Alert alert = new Alert(AlertType.INFORMATION);
                   	        alert.setHeaderText("Aviso");
                   	        alert.setContentText("Exito en eliminar el registro");
                   	        alert.showAndWait();
                    		opCliente.deleteCliente(id);
                    		TextId.setText("");

                    	    } else {
                    	     Alert fail= new Alert(AlertType.INFORMATION);
                       	     fail.setHeaderText("Error");
                       	     fail.setContentText("Hay uno o mas campos lleno no necesarios");
                       	     fail.showAndWait();
                       	     TextNombre.setText("");
                 	         TextApe.setText("");
                 	         TextDi.setText("");
                    	    }
            	    }
            }

        });
        btnM.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	int id= Integer.parseInt(TextId.getText());
            	String nombre =TextNombre.getText() ;
                String apellidos =TextApe.getText() ;
                String direccion =TextDi.getText() ;

            	if(TextApe.getText().trim().isEmpty()
            			&&TextDi.getText().trim().isEmpty()){
    	    		Alert alert = new Alert(AlertType.INFORMATION);
           	        alert.setHeaderText("Aviso");
           	        alert.setContentText("Exito en modificar el registro");
           	        alert.showAndWait();
            		opCliente.modificarClienteNom(id,nombre);
            		TextId.setText("");
            		TextNombre.setText("");
            		}else{
            			if(TextNombre.getText().trim().isEmpty()
                    			&&TextDi.getText().trim().isEmpty()){
            	    		Alert alert1 = new Alert(AlertType.INFORMATION);
                   	        alert1.setHeaderText("Aviso");
                   	        alert1.setContentText("Exito en modificar el registro");
                   	        alert1.showAndWait();
                    		opCliente.modificarClienteApe(id,apellidos);
                    		TextId.setText("");
                    		TextApe.setText("");

            		}else{
            			if(TextNombre.getText().trim().isEmpty()
                    			&&TextDi.getText().trim().isEmpty()){
            	    		Alert alert2 = new Alert(AlertType.INFORMATION);
                   	        alert2.setHeaderText("Aviso");
                   	        alert2.setContentText("Exito en modificar el registro");
                   	        alert2.showAndWait();
                    		opCliente.modificarClienteDi(id,direccion);
                    		TextId.setText("");
                	        TextDi.setText("");
                    		}else{
                    			Alert alert = new Alert(AlertType.INFORMATION);
                       	        alert.setHeaderText("Aviso");
                       	        alert.setContentText("Exito en modificar el registro");
                       	        alert.showAndWait();
                        		opCliente.modificarClienteAll(id,nombre,apellidos,direccion);
                        		TextId.setText("");
                        		TextNombre.setText("");
                    	        TextApe.setText("");
                    	        TextDi.setText("");
                    		}


                    		}



            	    }

            }

        });
        btnCI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	int id= Integer.parseInt(TextId.getText());
            	Cliente regCliente = opCliente.getCliente(id);

            	if(TextId.getText().trim().isEmpty()){
            		Alert fail= new Alert(AlertType.INFORMATION);
              	     fail.setHeaderText("Error");
              	     fail.setContentText("El campo Id necesita estar lleno para consultar");
              	     fail.showAndWait();
            	    }else{
            	    	if(TextNombre.getText().trim().isEmpty()
                    			&&TextApe.getText().trim().isEmpty()
                    			&&TextDi.getText().trim().isEmpty()){

            	    		Alert alert = new Alert(AlertType.INFORMATION);
                   	        alert.setHeaderText("Aviso");
                   	        alert.setContentText("Exito registro encontrado\n "+regCliente);
                   	        //opCliente.getClienteId(id);
                   	        alert.showAndWait();
                   	        TextId.setText("");
                   	        //System.out.println("Nuevo cliente: " + regCliente);

                   	     } else {
                    	     Alert fail= new Alert(AlertType.INFORMATION);
                       	     fail.setHeaderText("Error");
                       	     fail.setContentText("Hay uno o mas campos lleno no necesarios");
                       	     fail.showAndWait();
                       	     TextId.setText("");
                       	     TextNombre.setText("");
                 	         TextApe.setText("");
                 	         TextDi.setText("");
                    	    }
            	    }
            }

        });





        btnS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	System.exit(0);
            }
        });




    }

	public static void main(String[] args) {
		launch(args);
	}
}
