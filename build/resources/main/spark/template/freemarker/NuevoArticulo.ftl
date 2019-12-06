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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    <!--Custom styles-->
    <link rel="stylesheet" type="text/css" href="/css/styles3.css">
</head>
<body>

<div class="tab-pane fade show active" id="CreaArticulo" role="tabpanel" aria-labelledby="profile-tab">
    <!--INICIO CREAR ARTICULO POR EL ADMINISTRADOR-->

    <div class="container">
        <form method="post" action="/NuevoArticulo" role="dialog">
            <div class="d-flex justify-content-center h-100">
                <div class="card" id="CrearArticuloUnico">
                    <div class="card-header">
                        <h3>ARTICULO</h3>

                    </div>
                    <div class="card-body">
                        <form>
                            <div class="form-group">
                                <label for="titulo" style="font-family: 'Century Gothic'; color: #c8cbcf"><strong>TITULO </strong></label>
                                <input type="text" class="form-control"  aria-describedby="emailHelp" placeholder="" id="titulo" name="titulo">
                                <small id="titulo" class="form-text text-muted"></small>

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
                                <input type="submit" value="Publicar" class="btn float-right login_btn">
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">


                    </div>
                </div>
            </div>
        </form>
    </div>

</div>
<div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">No, menol</div>
</div>



</body>
</html>




