module edu.westga.cs3211.Pirate_Inventory_Ship_Manager {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens edu.westga.cs3211.Pirate_Inventory_Ship_Manager.view to javafx.fxml;
    exports edu.westga.cs3211.Pirate_Inventory_Ship_Manager;
}
