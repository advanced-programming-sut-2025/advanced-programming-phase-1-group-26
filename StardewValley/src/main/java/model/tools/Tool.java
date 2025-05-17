package model.tools;

import model.GameObject;
import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;

public class Tool extends GameObject {
     ToolType toolType;
     String name;

     public ToolType getToolType() {
          return toolType;
     }

     public void setToolType(ToolType toolType) {
          this.toolType = toolType;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     @Override
     public GameObjectType getObjectType()
     {
          return super.getObjectType();
     }
}
