package logic.Component;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BubbleExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawBubble(gc, 150, 150, 100);

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Bubble Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawBubble(GraphicsContext gc, double x, double y, double radius) {
        gc.setFill(Color.rgb(173, 216, 230, 0.5)); // light blue, semi-transparent
        gc.setStroke(Color.WHITE);    // slightly darker blue outline
        gc.setLineWidth(3);

        gc.fillOval(x, y, radius, radius);
        gc.strokeOval(x, y, radius, radius);

        // Add a highlight for a more "bubble" feel
        gc.setFill(Color.rgb(255, 255, 255, 0.3)); // soft white highlight
        gc.fillOval(x + radius * 0.2, y + radius * 0.2, radius * 0.3, radius * 0.3);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
