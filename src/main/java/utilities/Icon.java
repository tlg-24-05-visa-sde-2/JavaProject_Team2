package utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Icon extends JLabel implements DragGestureListener, DragSourceListener, DropTargetListener {
    private static final Logger LOGGER = Logger.getLogger(Icon.class.getName());
    private final DragSource dragSource;
    private final int tileSize;

    public Icon(ImageIcon icon, int tileSize) {
        super(icon);
        this.tileSize = tileSize;
        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, this);
        new DropTarget(this, this);
        setPreferredSize(new Dimension(tileSize, tileSize)); // Set the size of the icon
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        LOGGER.info("Drag gesture recognized");
        Transferable transferable = new TransferableIcon((Icon) getIcon());
        dragSource.startDrag(dge, DragSource.DefaultMoveDrop, transferable, this);
    }

    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
        LOGGER.info("DragSource: dragEnter");
    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {
        LOGGER.info("DragSource: dragOver");
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
        LOGGER.info("DragSource: dropActionChanged");
    }

    @Override
    public void dragExit(DragSourceEvent dse) {
        LOGGER.info("DragSource: dragExit");
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
        LOGGER.info("DragSource: dragDropEnd");
        if (dsde.getDropSuccess()) {
            LOGGER.info("Drop was successful");
            // Update the location based on where the drop occurred
            Component dropTarget = dsde.getDragSourceContext().getComponent();
            if (dropTarget instanceof GridButton) {
                GridButton button = (GridButton) dropTarget;
                setLocation(button.getX(), button.getY());
                LOGGER.info("Token moved to new location: " + button.getX() + ", " + button.getY());
            }
        }
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        LOGGER.info("DropTarget: dragEnter");
        dtde.acceptDrag(DnDConstants.ACTION_MOVE);
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        LOGGER.info("DropTarget: dragOver");
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        LOGGER.info("DropTarget: dropActionChanged");
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        LOGGER.info("DropTarget: dragExit");
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        LOGGER.info("DropTarget: drop");
        try {
            Transferable transferable = dtde.getTransferable();
            if (transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                Icon icon = (Icon) transferable.getTransferData(DataFlavor.imageFlavor);
                setIcon((javax.swing.Icon) icon);
                dtde.dropComplete(true);
                LOGGER.info("Drop complete");
                return;
            }
        } catch (UnsupportedFlavorException | IOException e) {
            LOGGER.log(Level.SEVERE, "Drop failed", e);
        }
        dtde.rejectDrop();
    }

    private static class TransferableIcon implements Transferable {
        private final Icon icon;

        public TransferableIcon(Icon icon) {
            this.icon = icon;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{DataFlavor.imageFlavor};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return DataFlavor.imageFlavor.equals(flavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) {
            if (!isDataFlavorSupported(flavor)) {
                try {
                    throw new UnsupportedFlavorException(flavor);
                } catch (UnsupportedFlavorException e) {
                    throw new RuntimeException(e);
                }
            }
            return icon;
        }
    }
}