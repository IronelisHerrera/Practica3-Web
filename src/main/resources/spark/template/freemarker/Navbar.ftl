<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->


<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <!--Made with love by Mutiullah Samim -->

    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    <!--Custom styles-->
    <link rel="stylesheet" type="text/css" href="/css/styles3.css">
</head>
<body>

    <ul class="nav justify-content-end">
            <li class="nav-item">
                <a class="nav-link active" style="color: #c8cbcf" href="/">INICIO</a>
            </li>
            <#if logueado?exists && Admin>
                <li class="nav-item">
                    <a class="nav-link" style="color: #c8cbcf" href="/AdministradorCreaUsuario">NUEVO USUARIO</a>
                </li>
            </#if>
        <#if logueado?exists && (Au || Admin)>
            <li class="nav-item">
                <a class="nav-link" style="color: #c8cbcf" href="/NuevoArticulo">NUEVO ARTICULO</a>
            </li>
        </#if>

        <!--PONER ESPACIOS-->
        <#if logueado?exists>
            <li class="nav-item" style="font-family: 'Century Gothic'">
                <a class="nav-link" style="color: #0b2e13" href="/CerrarSesion" id="CerrarSesion"
                   name="CerrarSesion"><strong>CERRAR SESION</strong></a>
            </li>
            <#else>
                <li class="nav-item" style="font-family: 'Century Gothic'">
                    <a class="nav-link" style="color: #0b2e13" href="/HomeLogin" id="CerrarSesion"
                       name="CerrarSesion"><strong>INICIAR SESION</strong></a>
                </li>
        </#if>

    </ul>

    <!--POST QUE HAYA HECHO EL USUARIO O ADMINISTRADOR
    Y CONDICION PARA MOSTRAR EL TEMPLATE--->
    <div class="d-flex justify-content-center h-100" id="PublicacionArticulos">
        <div class="row">

            <#list articulos as articulo>
                <div class="col-md-8">
                    <div class="card-body" id="PublicacionArticulos">
                        <div class="card-header" id="PublicacionArticulos">
                            <h4>${articulo.titulo} </h4> <!--Poner titulo-->
                        </div>
                        <div class="card-body" id="PublicacionArticulos">
                            <#if articulo.cuerpo?length gt 70>
                                <p class="card-text">${articulo.cuerpo?substring(0, 71)}...</p>
                                <#else>
                                    <p class="card-text">${articulo.cuerpo}</p>
                            </#if>
                            <a href="/VerArticulo/${articulo.id}"  class="btn btn-warning"  >Ver articulo</a>

                        </div>

                        <div class="card-footer " id="PublicacionArticulos">
                            <h6>Publicado el: ${articulo.fecha}  </h6>
                        </div>

                        <#list articulo.arregloEtiquetas as et>
                            <div class="card-footer " id="PublicacionArticulos">

                                <h6>Etiquetas: ${et.etiqueta}</h6>
                            </div>
                        </#list>
                    </div>
                </div>

            </#list>
        </div>
        <!--Para los tags-->
    </div>

</body>
</html>
