package metodapodstawiania;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.*;
import com.google.common.base.Predicate;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import org.apache.commons.collections15.*;

import java.awt.*;
import javax.swing.*;

public class SimpleGraph extends JFrame {
    public SimpleGraph (){
        super("Mój pierwszy graf");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Graph g = getGraph();

        Layout<Integer, String> layout = new CircleLayout(g);
        layout.setSize(new Dimension(300,300));
        VisualizationViewer<Integer,String> vv =
                new VisualizationViewer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(350,350));

        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm);

        JFrame frame = new JFrame("Prosta Interakcja");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }

    public Graph getGraph() {
        Graph<Integer, String> g = new SparseGraph<Integer, String>();
        g.addVertex((Integer)1);
        g.addVertex((Integer)2);
        g.addVertex((Integer)3);
        g.addVertex((Integer)4);
        g.addVertex((Integer)5);
        g.addEdge("Edge-A", 1, 2);
        g.addEdge("Edge-B", 2, 3);
        g.addEdge("Edge-C", 3, 1);
        g.addEdge("Edge-D", 4, 1);
        g.addEdge("Edge-E", 5, 2);
        return g;
    }
}
