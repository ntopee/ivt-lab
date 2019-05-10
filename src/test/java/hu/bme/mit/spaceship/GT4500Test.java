package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primary;
  private TorpedoStore secondary;

  @BeforeEach
  public void init(){
    primary = mock(TorpedoStore.class);
    secondary = mock(TorpedoStore.class);

    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(1)).isEmpty();
  }

  @Test
  public void fireSecondary_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireSecondarytwoTimes_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireSecondarrt_After_primary_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void firePrimary_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void firePrimarytwoTimes_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fire_ALL_Empty_Fail(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }

  @Test
  public void fire_ALL_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
  }

  public void fire_Laser(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireLaser(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
  }
  public void Rotate_bays(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    ship.fireTorpedo(FiringMode.ALL);
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(1)).fire(1);
        verify(secondary, times(1)).fire(1);
    assertEquals(true, result);
  }
}
