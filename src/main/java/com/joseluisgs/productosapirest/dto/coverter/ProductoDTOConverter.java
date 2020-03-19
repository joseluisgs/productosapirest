package com.joseluisgs.productosapirest.dto.coverter;

import com.joseluisgs.productosapirest.dto.CreateProductoDTO;
import com.joseluisgs.productosapirest.dto.ProductoDTO;
import com.joseluisgs.productosapirest.modelos.Producto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // Nos ahorramos el autowire
public class ProductoDTOConverter {

    private final ModelMapper modelMapper;


//	@PostConstruct
//	public void init() {
//		modelMapper.addMappings(new PropertyMap<Producto, ProductoDTO>() {
//
//			@Override
//			protected void configure() {
//				map().setCategoria(source.getCategoria().getNombre());
//			}
//		});
//	}

    // Recibe un producto y lo trasforma en productoDTO
    public ProductoDTO convertToDto(Producto producto) {
        return modelMapper.map(producto, ProductoDTO.class);

    }

    // Para convertir un prodtctoDTO en producto
    public Producto convertToProducto(CreateProductoDTO createProductoDTO) {
        return modelMapper.map(createProductoDTO, Producto.class);
    }


}
