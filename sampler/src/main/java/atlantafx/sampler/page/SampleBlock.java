/* SPDX-License-Identifier: MIT */
package atlantafx.sampler.page;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import net.datafaker.Faker;
import org.kordamp.ikonli.feather.Feather;

import java.util.Objects;
import java.util.Random;

public class SampleBlock extends VBox {

    public static final int BLOCK_HGAP = 20;
    public static final int BLOCK_VGAP = 10;

    protected static final Faker FAKER = new Faker();
    protected static final Random RANDOM = new Random();

    protected final Label titleLabel;
    protected final Node content; // can be either Pane or Control
    protected TextFlow descriptionText;

    public SampleBlock(String title, Node content) {
        this(title, content, null);
    }

    public SampleBlock(String title, Node content, String description) {
        titleLabel = new Label(Objects.requireNonNull(title));
        titleLabel.getStyleClass().add("title");

        this.content = Objects.requireNonNull(content);
        content.getStyleClass().add("content");

        getChildren().setAll(titleLabel, content);
        if (description != null && !description.isBlank()) {
            descriptionText = new TextFlow(new Text(description));
            getChildren().add(descriptionText);
        }

        getStyleClass().add("sample-block");
    }

    public String getTitle() {
        return titleLabel.getText();
    }

    public void setTitle(String text) {
        titleLabel.setText(text);
    }

    public Node getContent() {
        return content;
    }

    public void setFillHeight(boolean fillHeight) {
        if (fillHeight) {
            VBox.setVgrow(content, Priority.ALWAYS);
        } else {
            VBox.setVgrow(content, Priority.NEVER);
        }
    }

    protected static Feather randomIcon() {
        return Feather.values()[RANDOM.nextInt(Feather.values().length)];
    }
}
