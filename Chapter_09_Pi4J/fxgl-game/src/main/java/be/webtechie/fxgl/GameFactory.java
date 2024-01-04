package be.webtechie.fxgl;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import static com.almasb.fxgl.dsl.FXGL.texture;

import be.webtechie.fxgl.component.CloudComponent;
import be.webtechie.fxgl.component.PlayerComponent;
import com.almasb.fxgl.dsl.components.AutoRotationComponent;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * The factory which defines how each entity looks like
 */
public class GameFactory implements EntityFactory {

    /**
     * Types of objects we are going to use in our game.
     */
    public enum EntityType {
        BACKGROUND, CENTER, DUKE, CLOUD, BULLET
    }

    @Spawns("background")
    public Entity spawnBackground(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.BACKGROUND)
                .view(new Rectangle(data.<Integer>get("width"), data.<Integer>get("height"), Color.YELLOWGREEN))
                .with(new IrremovableComponent())
                .zIndex(-100)
                .build();
    }

    @Spawns("center")
    public Entity spawnCenter(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.CENTER)
                .collidable()
                .viewWithBBox(new Circle(data.<Integer>get("x"), data.<Integer>get("y"), data.<Integer>get("radius"), Color.DARKRED))
                .with(new IrremovableComponent())
                .zIndex(-99)
                .build();
    }

    @Spawns("duke")
    public Entity newDuke(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.DUKE)
                .viewWithBBox(texture("duke.png", 50, 50))
                .collidable()
                .with((new AutoRotationComponent()).withSmoothing())
                .with(new PlayerComponent())
                .build();
    }

    @Spawns("cloud")
    public Entity newCloud(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.CLOUD)
                .viewWithBBox(texture("cloud-network.png", 50, 50))
                .with((new AutoRotationComponent()).withSmoothing())
                .with(new CloudComponent())
                .collidable()
                .build();
    }

    @Spawns("bullet")
    public Entity newBullet(SpawnData data) {
        return entityBuilder(data)
                .type(EntityType.BULLET)
                .viewWithBBox(texture("sprite_bullet.png", 22, 11))
                .collidable()
                .with(new ProjectileComponent(data.get("direction"), 350), new OffscreenCleanComponent())
                .build();
    }
}
