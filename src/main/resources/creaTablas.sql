/*
  Script de creación de base de datos para 404 Market
  Este script crea tablas y
  carga datos de ejemplo.
*/
USE supermercado;

-- ===== LIMPIAR =====
DROP TABLE IF EXISTS ts_producto;
DROP TABLE IF EXISTS ts_categoria;

-- ===== CREAR TABLAS =====
CREATE TABLE ts_categoria (
    id_categoria  INT AUTO_INCREMENT PRIMARY KEY,
    descripcion   VARCHAR(50) NOT NULL UNIQUE,
    ruta_imagen   VARCHAR(1024),
    activo        BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE ts_producto (
    id_producto  INT AUTO_INCREMENT PRIMARY KEY,
    descripcion  VARCHAR(50) NOT NULL UNIQUE,
    detalle      TEXT,
    precio       DECIMAL(12,2) NOT NULL,
    existencias  INT NOT NULL DEFAULT 0,
    ruta_imagen  VARCHAR(1024),
    activo       BOOLEAN NOT NULL DEFAULT TRUE,
    id_categoria INT,
    descuento INT NOT NULL DEFAULT 0
    FOREIGN KEY (id_categoria) REFERENCES ts_categoria(id_categoria)
);

-- ===== CATEGORÍAS =====
INSERT INTO ts_categoria (descripcion, ruta_imagen, activo) VALUES
('Frutas y vegetales',        'https://images.unsplash.com/photo-1610832958506-aa56368176cf?w=200', true),
('Carnes y pescado',          'https://images.unsplash.com/photo-1607623814075-e51df1bdc82f?w=200', true),
('Lácteos y huevos',          'https://images.unsplash.com/photo-1628088062854-d1870b4553da?w=200', true),
('Pan, repostería y desayuno','https://images.unsplash.com/photo-1509440159596-0249088772ff?w=200', true),
('Dulces y snacks',           'https://images.unsplash.com/photo-1621939514649-280e2ee25f60?w=200', true),
('Comida congelada',          'https://images.unsplash.com/photo-1584568694244-14fbdf83bd30?w=200', true),
('Bebidas, café y té',        'https://images.unsplash.com/photo-1544145945-f90425340c7e?w=200', true),
('Vino, cerveza y licores',   'https://images.unsplash.com/photo-1510812431401-41d2bd2722f3?w=200', true);

-- ===== PRODUCTOS =====

-- Frutas y vegetales (id_categoria = 1)
INSERT INTO ts_producto (descripcion, detalle, precio, existencias, ruta_imagen, activo, id_categoria) VALUES
('Manzana Roja Kilo',    'Manzana roja fresca importada',          1500.00, 50, 'https://images.unsplash.com/photo-1567306226416-28f0efdc88ce?w=200', true, 1),
('Pera Kilo',            'Pera fresca de temporada',               1800.00, 40, 'https://images.unsplash.com/photo-1514756331096-242fdeb70d4a?w=200', true, 1),
('Lechuga Americana',    'Lechuga fresca lista para ensalada',      900.00, 30, 'https://images.unsplash.com/photo-1622205313162-be1d5712a43f?w=200', true, 1),
('Zanahoria Kilo',       'Zanahoria fresca nacional',               800.00, 60, 'https://images.unsplash.com/photo-1598170845058-32b9d6a5da37?w=200', true, 1);

-- Carnes y pescado (id_categoria = 2)
INSERT INTO ts_producto (descripcion, detalle, precio, existencias, ruta_imagen, activo, id_categoria) VALUES
('Bistec de res Kilo',   'Corte fresco de res nacional',           6500.00, 20, 'https://images.unsplash.com/photo-1603048297172-c92544798d5a?w=200', true, 2),
('Filete de tilapia Kg', 'Tilapia fresca sin espinas',             4200.00, 15, 'https://images.unsplash.com/photo-1519708227418-c8fd9a32b7a2?w=200', true, 2),
('Costilla de cerdo Kg', 'Costilla de cerdo fresca',               5800.00, 18, 'https://images.unsplash.com/photo-1432139555190-58524dae6a55?w=200', true, 2),
('Salmón Kilo',          'Salmón fresco importado',                9500.00,  8, 'https://images.unsplash.com/photo-1574781330855-d0db8cc6a79c?w=200', true, 2);

-- Lácteos y huevos (id_categoria = 3)
INSERT INTO ts_producto (descripcion, detalle, precio, existencias, ruta_imagen, activo, id_categoria) VALUES
('Leche Dos Pinos 1800ml','Leche entera pasteurizada',             1650.00, 45, 'https://images.unsplash.com/photo-1550583724-b2692b85b150?w=200', true, 3),
('Queso Turrialba 500g', 'Queso blanco fresco costarricense',      3200.00, 25, 'https://images.unsplash.com/photo-1486297678162-eb2a19b0a32d?w=200', true, 3),
('Natilla Zarcero 750g', 'Natilla espesa de Zarcero',              2100.00, 30, 'https://images.unsplash.com/photo-1587735243615-c03f25aaff15?w=200', true, 3),
('Cartón de huevos',     'Cartón de 30 huevos frescos',            3500.00, 55, 'https://images.unsplash.com/photo-1582722872445-44dc5f7e3c8f?w=200', true, 3);

-- Pan, repostería y desayuno (id_categoria = 4)
INSERT INTO ts_producto (descripcion, detalle, precio, existencias, ruta_imagen, activo, id_categoria) VALUES
('Pan cuadrado',         'Pan de molde blanco rebanado',            950.00, 40, 'https://images.unsplash.com/photo-1598373182133-52452f7691ef?w=200', true, 4),
('Pan Baguette',         'Baguette crujiente recién horneado',      700.00, 20, 'https://images.unsplash.com/photo-1549931319-a545dcf3bc7c?w=200', true, 4),
('Arrollado Dulce',      'Arrollado con azúcar y canela',           850.00, 15, 'https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=200', true, 4),
('Flauta dulce',         'Flauta de panadería con dulce de leche',  600.00, 25, 'https://images.unsplash.com/photo-1586444248902-2f64eddc13df?w=200', true, 4);

-- Dulces y snacks (id_categoria = 5)
INSERT INTO ts_producto (descripcion, detalle, precio, existencias, ruta_imagen, activo, id_categoria) VALUES
('Takis 75g',            'Snack de maíz enrollado picante',         950.00, 60, 'https://images.unsplash.com/photo-1599490659213-e2b9527bd087?w=200', true, 5),
('MMs 200g',             'Chocolates de colores surtidos',         1200.00, 50, 'https://images.unsplash.com/photo-1581798269827-5e80e9bba40c?w=200', true, 5),
('Tableta Hersheys',     'Chocolate con leche Hersheys',           1100.00, 45, 'https://images.unsplash.com/photo-1511381939415-e44c8dcdf946?w=200', true, 5),
('Panditas 1kg',         'Gomitas de osito surtidas',              2500.00, 30, 'https://images.unsplash.com/photo-1582058091597-8f05a80c5ea8?w=200', true, 5);

-- Comida congelada (id_categoria = 6)
INSERT INTO ts_producto (descripcion, detalle, precio, existencias, ruta_imagen, activo, id_categoria) VALUES
('Nuggets de pollo',     'Nuggets de pollo x20 unidades',          3200.00, 25, 'https://images.unsplash.com/photo-1562802378-063ec186a863?w=200', true, 6),
('Pizza Jamón',          'Pizza de jamón y queso individual',      2800.00, 20, 'https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=200', true, 6),
('Lasaña de carne',      'Lasaña de carne lista para calentar',    4500.00, 12, 'https://images.unsplash.com/photo-1619895092538-128341789043?w=200', true, 6),
('Papas Corte Recto',    'Papas fritas congeladas 500g',           2100.00, 35, 'https://images.unsplash.com/photo-1573080496219-bb080dd4f877?w=200', true, 6);

-- Bebidas, café y té (id_categoria = 7)
INSERT INTO ts_producto (descripcion, detalle, precio, existencias, ruta_imagen, activo, id_categoria) VALUES
('Coca Cola 1.5L',       'Gaseosa cola 1.5 litros',                1200.00, 80, 'https://images.unsplash.com/photo-1554866585-cd94860890b7?w=200', true, 7),
('Café Volio 500g',      'Café molido costarricense 100% arábica', 3800.00, 30, 'https://images.unsplash.com/photo-1559056199-641a0ac8b55e?w=200', true, 7),
('Té de manzanilla',     'Té de manzanilla en sobres x20',          850.00, 40, 'https://images.unsplash.com/photo-1556679343-c7306c1976bc?w=200', true, 7),
('Ginger Ale 500ml',     'Bebida gaseosa de jengibre',              950.00, 50, 'https://images.unsplash.com/photo-1527960471264-932f39eb5846?w=200', true, 7);

-- Vino, cerveza y licores (id_categoria = 8)
INSERT INTO ts_producto (descripcion, detalle, precio, existencias, ruta_imagen, activo, id_categoria) VALUES
('Cerveza Imperial',     'Cerveza lager costarricense 355ml',        900.00, 100, 'https://images.unsplash.com/photo-1608270586620-248524c67de9?w=200', true, 8),
('Cerveza Pilsen',       'Cerveza rubia costarricense 355ml',        850.00, 100, 'https://images.unsplash.com/photo-1535958636474-b021ee887b13?w=200', true, 8),
('Casillero Cab Sauv',   'Vino tinto chileno 750ml',                8500.00,  20, 'https://images.unsplash.com/photo-1510812431401-41d2bd2722f3?w=200', true, 8),
('Old Parr 12 años',     'Whisky escocés 750ml',                   32000.00,   8, 'https://images.unsplash.com/photo-1569529465841-dfecdab7503b?w=200', true, 8);

CREATE TABLE ts_carrito (

    id_carrito   INT AUTO_INCREMENT PRIMARY KEY,

    id_usuario   INT NOT NULL,

    id_producto  INT NOT NULL,

    cantidad     INT NOT NULL DEFAULT 1,

    CONSTRAINT fk_carrito_usuario  FOREIGN KEY (id_usuario)  REFERENCES ts_usuario(id_usuario),

    CONSTRAINT fk_carrito_producto FOREIGN KEY (id_producto) REFERENCES ts_producto(id_producto),

    CONSTRAINT uq_carrito UNIQUE (id_usuario, id_producto)

);
 
CREATE TABLE ts_pedido (

    id_pedido       INT AUTO_INCREMENT PRIMARY KEY,

    id_usuario      INT NOT NULL,

    fecha           DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    subtotal        DECIMAL(10,2) NOT NULL,

    impuesto        DECIMAL(10,2) NOT NULL,

    total           DECIMAL(10,2) NOT NULL,

    metodo_pago     VARCHAR(20) NOT NULL,  -- 'TARJETA' o 'ENTREGA'

    estado          VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',

    CONSTRAINT fk_pedido_usuario FOREIGN KEY (id_usuario) REFERENCES ts_usuario(id_usuario)

);
 
CREATE TABLE ts_detalle_pedido (

    id_detalle      INT AUTO_INCREMENT PRIMARY KEY,

    id_pedido       INT NOT NULL,

    id_producto     INT NOT NULL,

    cantidad        INT NOT NULL,

    precio_unitario DECIMAL(10,2) NOT NULL,

    subtotal        DECIMAL(10,2) NOT NULL,

    CONSTRAINT fk_detalle_pedido  FOREIGN KEY (id_pedido)  REFERENCES ts_pedido(id_pedido),

    CONSTRAINT fk_detalle_producto FOREIGN KEY (id_producto) REFERENCES ts_producto(id_producto)

);
 

 
CREATE TABLE ts_usuario (

    id_usuario   INT AUTO_INCREMENT PRIMARY KEY,

    username     VARCHAR(50)  NOT NULL UNIQUE,

    password     VARCHAR(200) NOT NULL,

    nombre       VARCHAR(100) NOT NULL,

    apellidos    VARCHAR(100) NOT NULL,

    correo       VARCHAR(100) NOT NULL UNIQUE,

    telefono     VARCHAR(20),

    activo       BOOLEAN NOT NULL DEFAULT FALSE,

    token        VARCHAR(200),

    ruta_imagen  VARCHAR(1024)

);
 
CREATE TABLE ts_role (

    id_role  INT AUTO_INCREMENT PRIMARY KEY,

    rol      VARCHAR(50) NOT NULL UNIQUE

);
 
CREATE TABLE ts_usuario_role (

    id_usuario INT NOT NULL,

    id_role    INT NOT NULL,

    PRIMARY KEY (id_usuario, id_role),

    FOREIGN KEY (id_usuario) REFERENCES ts_usuario(id_usuario),

    FOREIGN KEY (id_role)    REFERENCES ts_role(id_role)

);

  
-- Poner descuentos de prueba

UPDATE ts_producto SET descuento = 20 WHERE id_producto IN (1, 3, 6, 9, 12, 15);

UPDATE ts_producto SET descuento = 50 WHERE id_producto IN (2, 7, 11);
 