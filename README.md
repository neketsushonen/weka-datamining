Instalacin del proyecto
===============
    git clone https://github.com/neketsushonen/weka-datamining.git

    git fetch origin

    cd weka-datamining

    mvn eclipse:eclipse

Importar el proyecto desde eclipse


Tutorial 1: Ocupar Soundex para definir la agrupación de los productos
--------------
    git checkout tutorial/1-definirgrupo

1) Modificar la ruta de referncia escrita en el archivo cl.lai.datamining.App_definir_grupo
2) Ejecutar desde Eclipse cl.lai.datamining.App_definir_grupo
3) Comitear el cambio
  
    git add -u
    git commit -m "cambio de ruta"

Tutorial 2: Transformar la lista de transaccin en una tabla de cruce de información
--------------
    git checkout tutorial/2-listarfechagrupo

0) Aplicar el cambio en la rama anterior
    git merte --no-ff tutorial/1-definirgrupo
    
1) Ejecutar el archivo cl.lai.datamining.App_listar_transacciones
2) Ejecutarlo desde Eclipse cl.lai.datamining.App_listar_transacciones
3) Comitear el cambio
  
    git add -u
    git commit -m "cambio de ruta"


Tutorial 3: Listar las primeras reglas de asociación
--------------
    git checkout tutorial/3-reglasasociacion

0) Aplicar el cambio en la rama anterior
    git merte --no-ff  tutorial/2-listarfechagrupo

1) Modificar la ruta de referncia escrita en el archivo cl.lai.datamining.App_listar_reglas_asociacion
2) Ejecutar desde Eclipse archivo cl.lai.datamining.App_listar_reglas_asociacion

Tutorial 4: Listar las reglas de asociación según la venta del año 2012
--------------
    git checkout tutorial/4-reglasasociacion_anual
    
0) Aplicar el cambio en la rama anterior (no se aplica el cambio de turorial 3, pues sufriran muchos conflictos en el archivo)
    git merte --no-ff  tutorial/2-listarfechagrupo
1) Ejecutar: cl.lai.datamining.App_definir_grupo    
2) Ejecutar: cl.lai.datamining.App_listar_transacciones   
3) Modificar la ruta de referncia escrita en el archivo cl.lai.datamining.App_listar_reglas_asociacion 
4) Ejecutar: cl.lai.datamining.App_listar_reglas_asociacion   

Tutorial 5: Listar las reglas de asociación según la ventas de la temporada de navidad
--------------
    git checkout tutorial/5-reglasasociacion_temporada_navidad
    
0) Aplicar el cambio en la rama anterior (no se aplica el cambio de turorial 3, pues sufriran muchos conflictos en el archivo)
    git merte --no-ff  tutorial/2-listarfechagrupo
1) Ejecutar: cl.lai.datamining.App_definir_grupo    
2) Ejecutar: cl.lai.datamining.App_listar_transacciones   
3) Modificar la ruta de referncia escrita en el archivo cl.lai.datamining.App_listar_reglas_asociacion 
4) Ejecutar: cl.lai.datamining.App_listar_reglas_asociacion   

Tutorial 6: Listar las reglas de asociación según la ventas de un cierto mes
--------------
    git checkout tutorial/6-reglasasociacion_mes_producto
    
0) Aplicar el cambio en la rama anterior (no se aplica el cambio de turorial 3, pues sufriran muchos conflictos en el archivo)
    git merte --no-ff  tutorial/2-listarfechagrupo
1) Ejecutar: cl.lai.datamining.App_definir_grupo    
2) Ejecutar: cl.lai.datamining.App_listar_transacciones   
3) Modificar la ruta de referncia escrita en el archivo cl.lai.datamining.App_listar_reglas_asociacion 
4) Ejecutar: cl.lai.datamining.App_listar_reglas_asociacion   

Tutorial 7: Listar las reglas de asociación de los productos más comprados para un cierto mes
--------------
    git checkout tutorial/7-reglasasociacion_meses_producto_frecuente
    
0) Aplicar el cambio en la rama anterior (no se aplica el cambio de turorial 3, pues sufriran muchos conflictos en el archivo)
    git merte --no-ff  tutorial/2-listarfechagrupo
1) Ejecutar: cl.lai.datamining.App_definir_grupo    
2) Ejecutar: cl.lai.datamining.App_listar_transacciones   
3) Modificar la ruta de referncia escrita en el archivo cl.lai.datamining.App_listar_reglas_asociacion 
4) Ejecutar: cl.lai.datamining.App_listar_reglas_asociacion   

Tutorial 8: Definir campos a rescatar para una propiedad
--------------
    git checkout tutorial/8-definir_campos_propiedad
 
1) Modificar la ruta de referncia escrita en el archivo cl.lai.datamining.App_listar_reglas_asociacion_dpto 
2) Ejecutar: cl.lai.datamining.App_listar_reglas_asociacion_dpto 

Tutorial 9: Omitir las reglas muy obvias
--------------
    git checkout tutorial/9-omitir-reglas-insignificantes
    
1) Modificar la ruta de referncia escrita en el archivo cl.lai.datamining.App_listar_reglas_asociacion_dpto 
2) Ejecutar: cl.lai.datamining.App_listar_reglas_asociacion_dpto 


Tutorial 10: Relación entre las caracteristicas territoriales con el precio
--------------
    git checkout tutorial/10-relacion-con-precio
    
1) Modificar la ruta de referncia escrita en el archivo cl.lai.datamining.App_listar_reglas_asociacion_dpto 
2) Ejecutar: cl.lai.datamining.App_listar_reglas_asociacion_dpto 

Tutorial 11: Agrupar de manera objetiva el grupo de precio
--------------
    git checkout tutorial/11-precio-agrupado
    
1) Modificar la ruta de referncia escrita en el archivo cl.lai.datamining.App_listar_reglas_asociacion_dpto 
2) Ejecutar: cl.lai.datamining.App_listar_reglas_asociacion_dpto 
