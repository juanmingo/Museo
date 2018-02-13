CREATE TABLE "carrera_imparte" (
"cod_sede_carrera" int4 NOT NULL,
"cod_carrera" int4 NOT NULL,
"cod_sede_imparte" int4 NOT NULL,
"cod_mencion" int4 NOT NULL,
"sed_cod_sede" int4,
CONSTRAINT "pk_carrera_imparte" PRIMARY KEY ("cod_sede_carrera", "cod_carrera", "cod_sede_imparte", "cod_mencion") 
);

CREATE TABLE "carrera_sede" (
"sed_cod_sede" int4 NOT NULL,
"cod_carrera" int4 NOT NULL,
"nom_carrera" varchar(100) COLLATE "default" NOT NULL,
"abreviacion" varchar(50) COLLATE "default",
CONSTRAINT "pk_carrera_sede" PRIMARY KEY ("sed_cod_sede", "cod_carrera") 
);

CREATE TABLE "museo_proyecto" (
"muspro_id" int8 NOT NULL,
"muspro_nombre" varchar(1000) COLLATE "default",
"muspro_descripcion" varchar(4000),
"muspro_año" int4,
"cod_pais" int4 NOT NULL,
"cod_vigencia" int4 NOT NULL,
"mususu_id_usu" int8,
"fecha_modificacion" date,
CONSTRAINT "pk_museo_proyecto" PRIMARY KEY ("muspro_id") 
);

CREATE TABLE "museo_proyecto_detalle" (
"musprodet_id" int8 NOT NULL,
"muspro_id" int8 NOT NULL,
"musprodet_archivo" bytea,
"musprodet_nombre" varchar(1000) COLLATE "default",
"musprodet_descripcion" varchar(4000) COLLATE "default",
"cod_vigencia" int4 NOT NULL,
"mususu_id_usu" int8,
"fecha_modificacion" date,
CONSTRAINT "pk_museo_proyecto_detalle" PRIMARY KEY ("musprodet_id", "muspro_id") 
);

CREATE TABLE "museo_usuario" (
"mususu_id" int8 NOT NULL,
"cod_vigencia" int4 NOT NULL,
"cod_revision" int4 NOT NULL,
"mususu_nombres" varchar(100) COLLATE "default",
"mususu_paterno" varchar(50) COLLATE "default",
"mususu_materno" varchar(50) COLLATE "default",
"cod_pais" int4,
"mususu_fecha_nac" timestamp(0),
"correo" varchar(200) COLLATE "default",
"mususu_fono" varchar(20) COLLATE "default",
"mususu_ingreso" int4,
"mususu_rol" int8,
"mususu_rol_dv" int2,
"mususu_id_usu" int8,
"fecha_modificacion" date,
CONSTRAINT "pk_museo_usuario" PRIMARY KEY ("mususu_id") 
);

CREATE TABLE "museo_usuario_carrera" (
"mususu_id" int8 NOT NULL,
"sed_cod_sede" int4 NOT NULL,
"cod_carrera" int4 NOT NULL,
"sed_cod_sede_fisica" int4,
"mususu_carrera" varchar(200) COLLATE "default",
"mususu_ingreso" int4,
"mususu_id_usu" int8,
"fecha_modificacion" timestamp(0),
CONSTRAINT "pk_museo_usuario_carrera" PRIMARY KEY ("mususu_id", "sed_cod_sede", "cod_carrera") 
);

CREATE TABLE "museo_usuario_proyecto" (
"mususu_id" int8 NOT NULL,
"muspro_id" int8 NOT NULL,
"cod_vigencia" int4 NOT NULL,
"mususupro_cargo" varchar(1000) COLLATE "default",
"fecha_modificacion" date,
"rut_usuario" int8,
CONSTRAINT "pk_museo_usuario_proyecto" PRIMARY KEY ("mususu_id", "muspro_id") 
);

CREATE TABLE "pais" (
"cod_pais" int4 NOT NULL,
"nom_pais" varchar(80) COLLATE "default" NOT NULL,
"nom_nacionalidad" varchar(50) COLLATE "default",
"num_pais" int4,
CONSTRAINT "pk_pais" PRIMARY KEY ("cod_pais") 
);

CREATE TABLE "sede" (
"sed_cod_sede" int4 NOT NULL,
"sed_nom_sede" varchar(50) COLLATE "default" NOT NULL,
"sed_direccion" varchar(60) COLLATE "default" NOT NULL,
"sed_abreviacion" varchar(50) COLLATE "default" NOT NULL,
"sed_nombre_campus" varchar(20) COLLATE "default",
CONSTRAINT "pk_sede" PRIMARY KEY ("sed_cod_sede") 
);

CREATE TABLE "tipo_revision" (
"cod_revision" int4 NOT NULL,
"nom_revision" varchar(50) COLLATE "default",
CONSTRAINT "pk_tipo_revision" PRIMARY KEY ("cod_revision") 
);

CREATE TABLE "tipo_vigencia" (
"cod_vigencia" int4 NOT NULL,
"nom_vigencia" varchar(50) COLLATE "default",
CONSTRAINT "pk_tipo_vigencia" PRIMARY KEY ("cod_vigencia") 
);

CREATE TABLE "museo_proyecto_coordenada" (
"musprocoor_id" int8 NOT NULL,
"muspro_id" int8 NOT NULL,
"musprocoor_ciudad" varchar(1000) COLLATE "default",
"musprocoor_nombre" varchar(1000) COLLATE "default",
"musprocoor_latitud" float8,
"musprocoor_longitud" float8,
"cod_vigencia" int4 NOT NULL,
"mususu_id_usu" int8,
"fecha_modificacion" date,
CONSTRAINT "pk_museo_proyecto_coordenada" PRIMARY KEY ("musprocoor_id", "muspro_id") 
);

CREATE TABLE "museo_usuario_foto" (
"mususufo_id" int8 NOT NULL,
"mususu_id" int8 NOT NULL,
"mususufo_archivo" bytea,
"cod_vigencia" int4 NOT NULL,
"mususu_id_usu" int8,
"fecha_modificacion" date,
CONSTRAINT "pk_museo_foto_detalle" PRIMARY KEY ("mususufo_id", "mususu_id") 
);


ALTER TABLE "carrera_imparte" ADD CONSTRAINT "fk_mususupro_rel_carrimpsede" FOREIGN KEY ("sed_cod_sede") REFERENCES "sede" ("sed_cod_sede") MATCH FULL ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "carrera_imparte" ADD CONSTRAINT "fk_mususupro_rel_carrimpsede2" FOREIGN KEY ("cod_sede_carrera", "cod_carrera") REFERENCES "carrera_sede" ("sed_cod_sede", "cod_carrera") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "museo_proyecto" ADD CONSTRAINT "fk_muspro_rel_pai" FOREIGN KEY ("cod_pais") REFERENCES "pais" ("cod_pais") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "museo_proyecto_detalle" ADD CONSTRAINT "fk_musprodet_rel_musprodet" FOREIGN KEY ("muspro_id") REFERENCES "museo_proyecto" ("muspro_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "museo_usuario" ADD CONSTRAINT "fk_mususu_rel_tipvig" FOREIGN KEY ("cod_vigencia") REFERENCES "tipo_vigencia" ("cod_vigencia") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "museo_usuario" ADD CONSTRAINT "fk_mususu_rel_tiprev" FOREIGN KEY ("cod_revision") REFERENCES "tipo_revision" ("cod_revision") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "museo_usuario_carrera" ADD CONSTRAINT "fk_mususucar_rel_usu" FOREIGN KEY ("mususu_id") REFERENCES "museo_usuario" ("mususu_id") MATCH FULL ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "museo_usuario_carrera" ADD CONSTRAINT "fk_mususucar_rel_carsede" FOREIGN KEY ("sed_cod_sede", "cod_carrera") REFERENCES "carrera_sede" ("sed_cod_sede", "cod_carrera") MATCH FULL ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "museo_usuario_proyecto" ADD CONSTRAINT "fk_mususupro_rel_muspro" FOREIGN KEY ("muspro_id") REFERENCES "museo_proyecto" ("muspro_id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "museo_usuario_proyecto" ADD CONSTRAINT "fk_mususupro_rel_tipvig" FOREIGN KEY ("cod_vigencia") REFERENCES "tipo_vigencia" ("cod_vigencia") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "museo_usuario_proyecto" ADD CONSTRAINT "fk_mususupro_rel_musprousu" FOREIGN KEY ("mususu_id") REFERENCES "museo_usuario" ("mususu_id") MATCH FULL;
ALTER TABLE "museo_proyecto_coordenada" ADD CONSTRAINT "fk_musprocoor_rel_muspro" FOREIGN KEY ("muspro_id") REFERENCES "museo_proyecto" ("muspro_id");
ALTER TABLE "museo_proyecto_coordenada" ADD CONSTRAINT "fk_musprocoor_rel_tiprev" FOREIGN KEY ("cod_vigencia") REFERENCES "tipo_vigencia" ("cod_vigencia");
ALTER TABLE "museo_proyecto_detalle" ADD CONSTRAINT "fk_musprodet_rel_tiprev" FOREIGN KEY ("cod_vigencia") REFERENCES "tipo_vigencia" ("cod_vigencia");
ALTER TABLE "museo_proyecto" ADD CONSTRAINT "fk_muspror_rel_tiprev" FOREIGN KEY ("cod_vigencia") REFERENCES "tipo_vigencia" ("cod_vigencia");
ALTER TABLE "museo_usuario_carrera" ADD CONSTRAINT "fk_mususucar_rel_sedefisica" FOREIGN KEY ("sed_cod_sede_fisica") REFERENCES "sede" ("sed_cod_sede");
ALTER TABLE "museo_usuario_foto" ADD CONSTRAINT "fk_mususufot_rel_mususu" FOREIGN KEY ("mususu_id") REFERENCES "museo_usuario" ("mususu_id");
ALTER TABLE "museo_usuario_foto" ADD CONSTRAINT "fk_mususufot_rel_tiprev" FOREIGN KEY ("cod_vigencia") REFERENCES "tipo_vigencia" ("cod_vigencia");
ALTER TABLE "museo_usuario" ADD CONSTRAINT "fk_mususu_rel_pais" FOREIGN KEY ("cod_pais") REFERENCES "pais" ("cod_pais");
