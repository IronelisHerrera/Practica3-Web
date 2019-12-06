<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>


    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">


    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">


    <link rel="stylesheet" type="text/css" href="/css/styles3.css">
</head>
<body>
<#if logueado>

    <ul class="nav justify-content-md-center">
        <li class="nav-item">
            <a class="nav-link active" style="color: #c8cbcf" href="/">INICIO</a>
        </li>
    </ul>
    <div class="d-flex justify-content-center h-100" id="EdicionArticulo">
        <div class="row">
            <div class="col-md-18">
                <div class="card-body" >
                    <div class="card-header" >
                        <h4>${articulo.titulo} </h4>
                    </div>
                    <div class="card-body" >
                        <p class="card-text">${articulo.cuerpo}</p>
                        <#if logueado?exists && (Au?exists || Admin?exists)>
                            <a href="/EliminarArticulo/${articulo.id}" class="btn btn-warning">Eliminar</a>
                        </#if>
                    </div>

                    <div class="card-footer " >
                        <h6>Publicado el: ${articulo.fecha} </h6>
                    </div>


                    <#list articulo.arregloEtiquetas as et>
                        <div class="card-footer ">

                            <h6>Etiquetas:${et.etiqueta}</h6>
                        </div>
                    </#list>

                    <#if comentarios?exists>
                        <#list comentarios as comentario>
                            <div class="card-header"></div>
                            <div class="card-body">
                                <h6>${comentario.autor.username}</h6>
                                <p>${comentario.comentario}</p>
                                <#if logueado?exists && (Au?exists || Admin?exists)>
                                    <a href="/EliminarComentario/${articulo.id}/${comentario.id}" class="btn btn-warning">Eliminar</a>
                                </#if>
                            </div>
                        </#list>
                    </#if>
                    <#if logueado?exists && (Au?exists || Admin?exists)>

                 <div class="d-flex justify-content-center h-100">
                  <div class="tab-pane fade show active" id="CreaArticulo" role="tabpanel" aria-labelledby="profile-tab">

                  <div class="container">
                    <div class="card" id="CrearArticuloUnico2">
                        <div class="card-body">
                            <form action="/EditarArticulo/${articulo.id}" method="post">

                                <div class="form-group">
                                    <!--CUERPO DEL ARTICULO-->

                                    <label for="cuerpoTexto" style="font-family: 'Century Gothic'; color: #c8cbcf"><strong>CUERPO ARTICULO </strong></label>
                                    <textarea name="cuerpoTexto" rows="5" cols="38" id="cuerpoTexto" > </textarea>
                                    <small id="cuerpoTexto" class="form-text text-muted"></small>
                                    <!---AQUI VAN LAS ETIQUETAS--->

                                    <label for="Tags"style="font-family: 'Century Gothic'; color: #c8cbcf"><strong>ETIQUETAS </strong></label>
                                    <input type="text" class="form-control" id="Tags" aria-describedby="emailHelp" placeholder="" name="Tags">
                                    <small id="Tags" class="form-text text-muted"></small>

                                </div>


                                <div class="form-group">
                                    <input type="submit" value="Editar" class="btn float-right login_btn">
                                </div>
                                    </div>
                            </form>

                        </div>

                  </div>
                 </div>
                 </div>
                </div>
                <div class="container">
                    <div class="d-flex justify-content-center h-100" >
                        <div class="row">
                            <div class="card" id = "CrearArticuloUnico2">
                                <div class="card-header">Comentario</div>

                                <div class="card-body">
                                    <form class="col-11 py-5" method="post" action="/GuardarComentario/${articulo.id}">
                                        <div class="panel px-2 py-3 ">
                                            <textarea name="comentario" rows="5" cols="18" class="form-control rounded-0"></textarea>
                                        </div>

                                        <div class="align-content-start">
                                            <button class="btn btn-warning" type="submit">
                                                Comentar
                                            </button>
                                        </div>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </#if>
            </div>
     </div>
</div>
</div>
</div>
</#if>

</body>
</html>


