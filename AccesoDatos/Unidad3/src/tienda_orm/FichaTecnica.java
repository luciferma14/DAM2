package tienda_orm;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "ficha_tecnica")
public class FichaTecnica{

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false, precision = 8, scale = 2)
    private  BigDecimal peso;
    
    @Column (nullable = false, length = 50)
    private  String material;

    @Column (nullable = false, columnDefinition = "INT DEFAULT 0")
    private int garantia;

    @OneToOne 
    // Como queremos qeu la clace ajena esté en FichaTecnica, hacemos lo siguiente
    @JoinColumn (
        name = "id_producto",
        unique = true,
        nullable = false
    )
    private Producto producto;

    public FichaTecnica(){}

    public FichaTecnica(BigDecimal peso, String material, int garantia, Producto producto) {
        this.peso = peso;
        this.material = material;
        this.garantia = garantia;
        this.producto = producto;
        producto.setFichatecnica(this);
    }
}