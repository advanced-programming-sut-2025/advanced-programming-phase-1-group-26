package model.tools;

import model.GameObject;
import model.enums.GameObjectType;
import model.enums.tool_enums.ToolType;

public class Tool extends GameObject {
     ToolType type;
     String name;

     public ToolType getType() {
          return type;
     }

     public void setType(ToolType type) {
          this.type = type;
     }

     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }
}
