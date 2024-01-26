package Main

import javax.swing.JFrame
import javax.swing.JPanel
import java.awt.Dimension
import java.awt.Graphics
import java.awt.GraphicsEnvironment
import java.time.Instant
import java.time.Duration



// ################################################################################
// ################################################################################

object Main {

  // ###############################################################
  // ###############################################################

  def main(args:Array[String]):Unit={
    // var ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
    var window = new MyWindow()
    window.run()
  }

  // ###############################################################
  // ###############################################################

}

// ################################################################################
// ################################################################################

class MyWindow extends JFrame {

  // ###############################################################
  // ###############################################################

  val APP_WIDTH = 640
  val APP_HEIGHT = 480

  // ###############################################################
  // ###############################################################

  class App(W:Int,H:Int) extends JPanel {
    // constructor begins
    val WIDTH = W
    val HEIGHT = H

    this.setPreferredSize(new Dimension(APP_WIDTH, APP_HEIGHT))

    var stage = new Stage(WIDTH,HEIGHT)
    
    // ...
 
    override def paint(g:Graphics):Unit = {
      stage.paint(g)
    }
  }

  // ###############################################################
  // ###############################################################

  // constructor begins
  this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE)
  var canvas = new App(APP_WIDTH,APP_HEIGHT)
  this.setContentPane(canvas)
  this.pack()
  this.setVisible(true)

  // ###############################################################
  // ###############################################################

  def run(){
    while (true) {
        var startTime = Instant.now();
        this.repaint();
        var endTime = Instant.now();
        var howLong = Duration.between(startTime, endTime).toMillis();
        try {
            Thread.sleep(20L - howLong);
        } catch{
          //...
          case e:InterruptedException => {
              System.out.println("thread was interrupted, but who cares?");
          }
          case e:IllegalArgumentException => {
              System.out.println("application can't keep up with framerate");
          }
        }
    }
  }

  // ###############################################################
  // ###############################################################

}

// ################################################################################
// ################################################################################

