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
    <link rel="stylesheet" type="text/css" href="/css/style2.css">
</head>
<body>
<div class="container">
    <form method="post" action="/loginAdministrador" role="dialog">
        <div class="d-flex justify-content-center h-100">
            <div class="card">
                <div class="card-header">
                    <h3>ADMINISTRACION</h3>

                </div>
                <div class="card-body"  >
                    <form>
                        <div class="form-group">
                            <button type="submit" value="CREAR USUARIO" class="btn btn-danger"><i class="fas fa-user"></i></button>
                            <small class="small" style="font-family: 'Century Gothic'; color: aliceblue"><strong>CREAR USUARIO</strong> </small>
                        </div>

                        <div class="dropdown-divider"></div>

                        <div class="form-group">
                            <button type="submit" value="CREAR ARTICULO" class="btn btn-light" style="size: 1000cm"><i class="fas fa-newspaper" ></i></button>
                            <small class="small" style="font-family: 'Century Gothic'; color: aliceblue"><strong>CREAR ARTICULO</strong> </small>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </form>
</div>
</body>
</html>