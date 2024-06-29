package de.thm.mni.compilerbau.phases._05_varalloc;

import de.thm.mni.compilerbau.utils.NotImplemented;

/**
 * This class describes the stack frame layout of a procedure.
 * It contains the sizes of the various subareas and provides methods to retrieve information about the stack frame required to generate code for the procedure.
 */
public class StackLayout {
    // The following values have to be set in phase 5
    public Integer argumentAreaSize = 0; // Für jedes Argument gibt es einen offset , die benutzt werden muss
    public Integer localVarAreaSize = 0;// Für jede lovale Variable gibt es auch einen Offset
    public Integer outgoingAreaSize = 0; // Maximum  an Platzbedarf für die Argumente der aufrufenden Prozeduren


    /**
     * Die richtige Implementierung kann man im script finden
     */
    /**
     * @return The total size of the stack frame described by this object.
     */
    public int frameSize() {
        //TODO (assignment 5): Calculate the size of the stack frame
        return 8+localVarAreaSize + outgoingAreaSize;


    }

    /**
     * @return The offset (starting from the new stack pointer) where the old frame pointer is stored in this stack frame.
     */
    public int oldFramePointerOffset() {
        //TODO (assignment 5): Calculate the offset of the old frame pointer
return  outgoingAreaSize +4 ;
    }

    /**
     * @return The offset (starting from the new frame pointer) where the old return address is stored in this stack frame.
     */
    public int oldReturnAddressOffset() {
        //TODO (assignment 5): Calculate the offset of the old return address
        return  -8-localVarAreaSize;

    }
}
