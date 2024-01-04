package be.webtechie.fxgl.component;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.component.Component;
import javafx.geometry.Point2D;

import static com.almasb.fxgl.dsl.FXGL.getAppHeight;
import static com.almasb.fxgl.dsl.FXGL.getAppWidth;

public class CloudComponent extends Component {

    private final Point2D direction = new Point2D(FXGLMath.random(-1D, 1D), FXGLMath.random(-1D, 1D));

    /**
     * tpf = Time per frame
     * <p>
     * onUpdate() will be called 120 times per second on a 120hz monitor (provided the CPU/GPU are fast enough),
     * but the value of tpf won't be 0.0166(6), it will be 0.00833(3) since tpf = 1 / fps.
     * Hence if you take tpf into account in your calculation, the result will be the same regardless of the refresh rate.
     * <p>
     * For example,
     * start x = 0,
     * update x += 2 * tpf.
     * <p>
     * After 1 second (at 60fps), x is ~2, because x = 2 * 0.0167 * 60
     * After 1 second (at 120fps), x is also ~2, because x = 2 * 0.00833 * 120
     * <p>
     * Whilst the actual values may not be identical (they won't be identical even if two machines run at 60fps,
     * or even if running two instances of the game on the same machine), they will be very close.
     */
    @Override
    public void onUpdate(double tpf) {
        // We want the clouds to move faster than the player
        entity.translate(direction.multiply(tpf * 100));
        checkForBounds();
    }

    private void checkForBounds() {
        if (entity.getX() < 0) {
            remove();
        }
        if (entity.getX() >= getAppWidth()) {
            remove();
        }
        if (entity.getY() < 0) {
            remove();
        }
        if (entity.getY() >= getAppHeight()) {
            remove();
        }
    }

    public void remove() {
        entity.removeFromWorld();
    }
}