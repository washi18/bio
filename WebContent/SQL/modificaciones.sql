CREATE OR REPLACE FUNCTION Pricing_sp_BuscarReservasInicial(
		fechaActual varchar(12)
)
RETURNS table (creservacod varchar(12),dfechainicio Date,dfechafin Date,dfecha timestamp,categoriaHotelcod int,ccontacto varchar(12),
cemail varchar(100),ctelefono varchar(50),nnropersonas int,npreciopaquetepersona numeric,ctituloidioma1 varchar(200),
ccategoriaidioma1 varchar(200),cestado varchar(20),porcentajecobro int,pagominimo int,modoporcentual boolean) AS
$$
	select r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,COALESCE( c.categoriahotelcod, 0 ),r.ccontacto,r.cemail,r.ctelefono,r.nnropersonas,r.npreciopaquetepersona,
		p.ctituloidioma1,c.ccategoriaidioma1,r.cestado,p.nporcentajecobro,p.npagominimo,p.bmodoporcentual
			from treserva as r 
			left join treservapaquete as rp on(r.creservacod=rp.creservacod)
			left join treservapaquetecategoriahotel as rpch on(rp.nreservapaquetecod=rpch.nreservapaquetecod)
			left join tpaquetecategoriahotel as pch on(rpch.codpaquetecategoriah=pch.codpaquetecategoriah)
			left join tcategoriahotel as c on(pch.categoriahotelcod=c.categoriahotelcod)
			left join tpaquete as p on(p.cpaquetecod=rp.cpaquetecod)
			where (r.dfecha<=(to_date($1,'yyyy-MM-dd'))+'1 day'::interval) and r.cestado='PENDIENTE DE PAGO'
			group by r.creservacod,r.dfechainicio,r.dfechafin,r.dfecha,c.categoriahotelcod,r.ccontacto,r.cemail,r.ctelefono,r.nnropersonas,r.npreciopaquetepersona,
					p.ctituloidioma1,c.ccategoriaidioma1,r.cestado,p.nporcentajecobro,p.npagominimo,p.bmodoporcentual
			order by r.dfecha desc
			limit 10;
$$
  LANGUAGE sql;
CREATE OR REPLACE FUNCTION Pricing_sp_RegistrarImpuesto
(
	cImpuestoPaypal varchar(5),
	cImpuestoPaytoPeru varchar(5)
)
RETURNS TABLE (resultado varchar(20),mensaje varchar(200),codImp int) as
$$
begin
	codImp=(select codimpuesto from timpuesto);
	if(codImp is null)then
		insert into timpuesto values(1,$1,$2);
		mensaje='Datos Registrados Correctamente';
	else
		update timpuesto set impuestopaypal=$1,impuestopaytoperu=$2 where codimpuesto=1;
			mensaje='Datos Actualizados Correctamente';
	end if;
	resultado='correcto';
	return Query select resultado,mensaje,1;
end
$$
LANGUAGE plpgsql;
create or replace function Pricing_sp_EliminarServicio
(
	codServicio integer
)
returns table (resultado varchar(20),mensaje varchar(200))as
$$
begin
	delete from tservicio where nserviciocod=$1;
	if(exists(select * from tservicio where nserviciocod=$1))then
		resultado='incorrecto';
		mensaje='Datos No Eliminados Correctamente';	
	else
		resultado='correcto';
		mensaje='Datos Eliminados Correctamente';
	end if;
	return Query select resultado,mensaje;
end 
$$
language plpgsql;

/***************************************************/
create or replace function Pricing_sp_EliminarSubServicio
(
	codSubServicio integer
)
returns table (resultado varchar(20),mensaje varchar(200))as
$$
begin
	delete from tsubservicio where nsubserviciocod=$1;
	if(exists(select * from tsubservicio where nsubserviciocod=$1))then
		resultado='incorrecto';
		mensaje='Datos No Eliminados Correctamente';	
	else
		resultado='correcto';
		mensaje='Datos Eliminados Correctamente';
	end if;
	return Query select resultado,mensaje;
end 
$$
language plpgsql;

create or replace function Pricing_sp_EliminarActividad
(
	codActividad integer
)
returns table (resultado varchar(20),mensaje varchar(200))as
$$
begin
	delete from tactividad where nactividadcod=$1;
	if(exists(select * from tactividad where nactividadcod=$1))then
		resultado='incorrecto';
		mensaje='Datos No Eliminados Correctamente';	
	else
		resultado='correcto';
		mensaje='Datos Eliminados Correctamente';
	end if;
	return Query select resultado,mensaje;
end 
$$
language plpgsql;

create or replace function Pricing_sp_EliminarHotel
(
	codHotel integer
)
returns table (resultado varchar(20),mensaje varchar(200))as
$$
begin
	delete from thotel where nhotelcod=$1;
	if(exists(select * from thotel where nhotelcod=$1))then
		resultado='incorrecto';
		mensaje='Datos No Eliminados Correctamente';	
	else
		resultado='correcto';
		mensaje='Datos Eliminados Correctamente';
	end if;
	return Query select resultado,mensaje;
end 
$$
language plpgsql;

create or replace function Pricing_sp_EliminarDestino
(
	codDestino integer
)
returns table (resultado varchar(20),mensaje varchar(200))as
$$
begin
	delete from tdestino where ndestinocod=$1;
	if(exists(select * from tdestino where ndestinocod=$1))then
		resultado='incorrecto';
		mensaje='Datos No Eliminados Correctamente';	
	else
		resultado='correcto';
		mensaje='Datos Eliminados Correctamente';
	end if;
	return Query select resultado,mensaje;
end 
$$
language plpgsql;

create or replace function Pricing_sp_EliminarCupon
(
	codCupon integer
)
returns table (resultado varchar(20),mensaje varchar(200))as
$$
begin
	delete from tcupon where ncuponcod=$1;
	if(exists(select * from tcupon where ncuponcod=$1))then
		resultado='incorrecto';
		mensaje='Datos No Eliminados Correctamente';	
	else
		resultado='correcto';
		mensaje='Datos Eliminados Correctamente';
	end if;
	return Query select resultado,mensaje;
end 
$$
language plpgsql;

create or replace function pricing_sp_eliminarusuario
(
	codUsuario varchar(150)
)
returns table (resultado varchar(20),mensaje varchar(200))as
$$
begin
	if((select nperfilcod from tusuario where cusuariocod=$1) == 1) then
		delete from tpaquete where cpaquetecod=$1;
		if(exists(select * from tpaquete where cpaquetecod=$1))then
			resultado='incorrecto';
			mensaje='Datos No Eliminados Correctamente';	
		else
			resultado='correcto';
			mensaje='Datos Eliminados Correctamente';
		end if;
	else
		resultado='super_usuario';
		mensaje='No se puede eliminar al super usuario';
	end if;
	return Query select resultado,mensaje;
end
$$
language plpgsql;
CREATE TABLE tproceso
(
  nroproceso integer NOT NULL,
  descripcion character varying(250),
  CONSTRAINT nroproceso PRIMARY KEY (nroproceso)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tproceso
  OWNER TO postgres;

CREATE TABLE tlistaproceso
(
  ncodlistaproceso integer not null,
  codetiqueta integer,
  nroproceso integer,
  CONSTRAINT ncodlistaproceso PRIMARY KEY (ncodlistaproceso),
  CONSTRAINT codetiqueta FOREIGN KEY (codetiqueta) REFERENCES tetiqueta (codetiqueta) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT nroproceso FOREIGN KEY (nroproceso) REFERENCES tproceso (nroproceso) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tlistaproceso
  OWNER TO postgres;
  
ALTER TABLE timpuesto ADD COLUMN impuestoPaytoPeru varchar(5) DEFAULT '10';
alter table timpuesto alter column impuestoPaytoPeru drop default;

create table TPagoPaytoPeru
(
	nPaytoPeruPagoCod bigint,
	codigo_transaccion varchar(25),
	forma_pago varchar(20),
	medio_pago varchar(20),
	importe_autorizado decimal(10,2),
	numero_pedido int,
	codigo_autorizacion varchar(10),
	numero_tarjeta varchar(24),
	nombre_tarjeta_habiente varchar(200),
	email varchar(150),
	fecha varchar(20),
	primary key (nPaytoPeruPagoCod)
)

create or replace function Pricing_sp_RegistrarPagoPaytoPeru
(
	codigo_transaccion varchar(25),
	forma_pago varchar(20),
	medio_pago varchar(20),
	importe_autorizado decimal(10,2),
	numero_pedido int,
	codigo_autorizacion varchar(10),
	numero_tarjeta varchar(24),
	nombre_tarjeta_habiente varchar(200),
	email varchar(150),
	fecha varchar(20)
)
returns table(resultado varchar(20),mensaje varchar(200),codReserva varchar(25))as
$$
declare
	codPagos bigint;
begin
    codReserva=$1;
    codPagos=(select max(nPaytoPeruPagoCod) from tpagopaytoperu);
    if(codPagos is null)then
        codPagos=1;
    else
        codPagos=codPagos+1;
    end if;
    insert into tpagopaytoperu values(codPagos,$1,$2,$3,$4,$5,$6,$7,$8,$9,$10);
    if(exists(select * from tpagopaytoperu where npaytoperupagocod=codPagos))then
	resultado='correcto';
    	mensaje='Datos Registrados Correctamente';
    else
	resultado='error';
    	mensaje='Datos No Registrados';
    end if;
    return Query select resultado,mensaje,codReserva;
end 
$$
language plpgsql;

create or replace function Pricing_sp_UpdateEstadoReserva
(
	montoPagado numeric(10,2),
	medioPago varchar(20),
	reservaCod varchar(12)	
)
returns table(resultado varchar(20),mensaje varchar(200),codReserva varchar(12))as
$$
declare
	montoTotal numeric(10,2);
begin
    codReserva=$3;
    montoTotal=(select (nmontototalpaquete) from treservapaquete where creservacod=$3);
    if(montoTotal>$1)then
	update treserva set cestado='PAGO PARCIAL',
			cmetodopago=$2 where creservacod=$3;
    else
	update treserva set cestado='PAGO TOTAL',
			cmetodopago=$2 where creservacod=$3;
    end if;
	resultado='correcto';
    	mensaje='Datos Registrados Correctamente';
    return Query select resultado,mensaje,codReserva;
end 
$$
language plpgsql;

ALTER TABLE thotel
DROP CONSTRAINT thotel_categoriahotelcod_fkey,
ADD CONSTRAINT thotel_categoriahotelcod_fkey FOREIGN KEY (categoriahotelcod)
   REFERENCES public.tcategoriahotel (categoriahotelcod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE thotel
DROP CONSTRAINT thotel_ndestinocod_fkey,
ADD CONSTRAINT thotel_ndestinocod_fkey FOREIGN KEY (ndestinocod)
      REFERENCES public.tdestino (ndestinocod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tacceso
DROP CONSTRAINT tacceso_nperfilcod_fkey,
ADD CONSTRAINT tacceso_nperfilcod_fkey FOREIGN KEY (nperfilcod)
      REFERENCES public.tperfil (nperfilcod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tconfigaltonivel
DROP CONSTRAINT tconfigaltonivel_nperfilcod_fkey,
ADD CONSTRAINT tconfigaltonivel_nperfilcod_fkey FOREIGN KEY (nperfilcod)
      REFERENCES public.tperfil (nperfilcod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tfechaalterna
DROP CONSTRAINT tfechaalterna_creservacod_fkey,
ADD CONSTRAINT tfechaalterna_creservacod_fkey FOREIGN KEY (creservacod)
      REFERENCES public.treserva (creservacod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tgaleriahotel
DROP CONSTRAINT tgaleriahotel_nhotelcod_fkey,
ADD CONSTRAINT tgaleriahotel_nhotelcod_fkey FOREIGN KEY (nhotelcod)
      REFERENCES public.thotel (nhotelcod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tgaleriapaquete
DROP CONSTRAINT tgaleriapaquete_cpaquetecod_fkey,
ADD CONSTRAINT tgaleriapaquete_cpaquetecod_fkey FOREIGN KEY (cpaquetecod)
      REFERENCES public.tpaquete (cpaquetecod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tpagopaypal
DROP CONSTRAINT tpagopaypal_creservacod_fkey,
ADD CONSTRAINT tpagopaypal_creservacod_fkey FOREIGN KEY (creservacod)
      REFERENCES public.treserva (creservacod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tpagovisa
DROP CONSTRAINT tpagovisa_creservacod_fkey,
ADD CONSTRAINT tpagovisa_creservacod_fkey FOREIGN KEY (creservacod)
      REFERENCES public.treserva (creservacod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tpaqueteactividad
DROP CONSTRAINT tpaqueteactividad_cpaquetecod_fkey,
ADD CONSTRAINT tpaqueteactividad_cpaquetecod_fkey FOREIGN KEY (cpaquetecod)
      REFERENCES public.tpaquete (cpaquetecod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tpaqueteactividad
DROP CONSTRAINT tpaqueteactividad_nactividadcod_fkey,
ADD CONSTRAINT tpaqueteactividad_nactividadcod_fkey FOREIGN KEY (nactividadcod)
      REFERENCES public.tactividad (nactividadcod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tpaquetecategoriahotel
DROP CONSTRAINT tpaquetecategoriahotel_categoriahotelcod_fkey,
ADD CONSTRAINT tpaquetecategoriahotel_categoriahotelcod_fkey FOREIGN KEY (categoriahotelcod)
      REFERENCES public.tcategoriahotel (categoriahotelcod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tpaquetecategoriahotel
DROP CONSTRAINT tpaquetecategoriahotel_cpaquetecod_fkey,
ADD CONSTRAINT tpaquetecategoriahotel_cpaquetecod_fkey FOREIGN KEY (cpaquetecod)
      REFERENCES public.tpaquete (cpaquetecod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tpaquetedestino
DROP CONSTRAINT tpaquetedestino_cpaquetecod_fkey,
ADD CONSTRAINT tpaquetedestino_cpaquetecod_fkey FOREIGN KEY (cpaquetecod)
      REFERENCES public.tpaquete (cpaquetecod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tpaquetedestino
DROP CONSTRAINT tpaquetedestino_ndestinocod_fkey,
ADD CONSTRAINT tpaquetedestino_ndestinocod_fkey FOREIGN KEY (ndestinocod)
      REFERENCES public.tdestino (ndestinocod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tpaqueteservicio
DROP CONSTRAINT tpaqueteservicio_cpaquetecod_fkey,
ADD CONSTRAINT tpaqueteservicio_cpaquetecod_fkey FOREIGN KEY (cpaquetecod)
      REFERENCES public.tpaquete (cpaquetecod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tpaqueteservicio
DROP CONSTRAINT tpaqueteservicio_nserviciocod_fkey,
ADD CONSTRAINT tpaqueteservicio_nserviciocod_fkey FOREIGN KEY (nserviciocod)
      REFERENCES public.tservicio (nserviciocod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tpasajero
DROP CONSTRAINT tpasajero_creservacod_fkey,
ADD CONSTRAINT tpasajero_creservacod_fkey FOREIGN KEY (creservacod)
      REFERENCES public.treserva (creservacod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tpasajero
DROP CONSTRAINT tpasajero_npaiscod_fkey,
ADD CONSTRAINT tpasajero_npaiscod_fkey FOREIGN KEY (npaiscod)
      REFERENCES public.tpais (npaiscod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tpasajero
DROP CONSTRAINT tpasajero_ntipodoc_fkey,
ADD CONSTRAINT tpasajero_ntipodoc_fkey FOREIGN KEY (ntipodoc)
      REFERENCES public.ttipodocumento (ntipodoc) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE treservacupon
DROP CONSTRAINT treservacupon_creservacod_fkey,
ADD CONSTRAINT treservacupon_creservacod_fkey FOREIGN KEY (creservacod)
      REFERENCES public.treserva (creservacod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE treservacupon
DROP CONSTRAINT treservacupon_ncuponcod_fkey,
ADD CONSTRAINT treservacupon_ncuponcod_fkey FOREIGN KEY (ncuponcod)
      REFERENCES public.tcupon (ncuponcod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE treservapaquete
DROP CONSTRAINT treservapaquete_cpaquetecod_fkey,
ADD CONSTRAINT treservapaquete_cpaquetecod_fkey FOREIGN KEY (cpaquetecod)
      REFERENCES public.tpaquete (cpaquetecod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE treservapaquete
DROP CONSTRAINT treservapaquete_creservacod_fkey,
ADD CONSTRAINT treservapaquete_creservacod_fkey FOREIGN KEY (creservacod)
      REFERENCES public.treserva (creservacod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE treservapaqueteactividad
DROP CONSTRAINT treservapaqueteactividad_nreservapaquetecod_fkey,
ADD CONSTRAINT treservapaqueteactividad_nreservapaquetecod_fkey FOREIGN KEY (nreservapaquetecod)
      REFERENCES public.treservapaquete (nreservapaquetecod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE treservapaquetecategoriahotel
DROP CONSTRAINT treservapaquetecategoriahotel_codpaquetecategoriah_fkey,
ADD CONSTRAINT treservapaquetecategoriahotel_codpaquetecategoriah_fkey FOREIGN KEY (codpaquetecategoriah)
      REFERENCES public.tpaquetecategoriahotel (codpaquetecategoriah) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE treservapaquetecategoriahotel
DROP CONSTRAINT treservapaquetecategoriahotel_nreservapaquetecod_fkey,
ADD CONSTRAINT treservapaquetecategoriahotel_nreservapaquetecod_fkey FOREIGN KEY (nreservapaquetecod)
      REFERENCES public.treservapaquete (nreservapaquetecod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE treservapaqueteservicio
DROP CONSTRAINT treservapaqueteservicio_codpaqueteservicio_fkey,
ADD CONSTRAINT treservapaqueteservicio_codpaqueteservicio_fkey FOREIGN KEY (codpaqueteservicio)
      REFERENCES public.tpaqueteservicio (codpaqueteservicio) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE treservapaqueteservicio
DROP CONSTRAINT treservapaqueteservicio_nreservapaquetecod_fkey,
ADD CONSTRAINT treservapaqueteservicio_nreservapaquetecod_fkey FOREIGN KEY (nreservapaquetecod)
      REFERENCES public.treservapaquete (nreservapaquetecod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tsubservicio
DROP CONSTRAINT tsubservicio_nserviciocod_fkey,
ADD CONSTRAINT tsubservicio_nserviciocod_fkey FOREIGN KEY (nserviciocod)
      REFERENCES public.tservicio (nserviciocod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE tusuario
DROP CONSTRAINT tusuario_nperfilcod_fkey,
ADD CONSTRAINT tusuario_nperfilcod_fkey FOREIGN KEY (nperfilcod)
      REFERENCES public.tperfil (nperfilcod) MATCH SIMPLE
   ON UPDATE CASCADE ON DELETE CASCADE;
   
   
ALTER TABLE timpuesto DROP COLUMN impuestovisa;
ALTER TABLE timpuesto DROP COLUMN impuestomastercard;
ALTER TABLE timpuesto DROP COLUMN impuestodinnersclub;
ALTER TABLE timpuesto DROP COLUMN porcentajecobro;
ALTER TABLE timpuesto DROP COLUMN pagominimo;
ALTER TABLE timpuesto DROP COLUMN modoporcentual;