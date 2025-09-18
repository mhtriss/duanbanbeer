<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>


<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style>
    .shopping-cart{
        padding-bottom: 50px;
        font-family: 'Montserrat', sans-serif;
    }

    .shopping-cart.dark{
        background-color: #f6f6f6;
    }

    .shopping-cart .content{
        box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.075);
        background-color: white;
    }

    .shopping-cart .block-heading{
        padding-top: 50px;
        margin-bottom: 40px;
        text-align: center;
    }

    .shopping-cart .block-heading p{
        text-align: center;
        max-width: 420px;
        margin: auto;
        opacity:0.7;
    }

    .shopping-cart .dark .block-heading p{
        opacity:0.8;
    }

    .shopping-cart .block-heading h1,
    .shopping-cart .block-heading h2,
    .shopping-cart .block-heading h3 {
        margin-bottom:1.2rem;
        color: #3b99e0;
    }

    .shopping-cart .items{
        margin: auto;
    }

    .shopping-cart .items .product{
        margin-bottom: 20px;
        padding-top: 20px;
        padding-bottom: 20px;
    }

    .shopping-cart .items .product .info{
        padding-top: 0px;
        text-align: center;
    }

    .shopping-cart .items .product .info .product-name{
        font-weight: 600;
    }

    .shopping-cart .items .product .info .product-name .product-info{
        font-size: 14px;
        margin-top: 15px;
    }

    .shopping-cart .items .product .info .product-name .product-info .value{
        font-weight: 400;
    }

    .shopping-cart .items .product .info .quantity .quantity-input{
        margin: auto;
        width: 80px;
    }

    .shopping-cart .items .product .info .price{
        margin-top: 15px;
        font-weight: bold;
        font-size: 22px;
    }

    .shopping-cart .summary{
        border-top: 2px solid #5ea4f3;
        background-color: #f7fbff;
        height: 100%;
        padding: 30px;
    }

    .shopping-cart .summary h3{
        text-align: center;
        font-size: 1.3em;
        font-weight: 600;
        padding-top: 20px;
        padding-bottom: 20px;
    }

    .shopping-cart .summary .summary-item:not(:last-of-type){
        padding-bottom: 10px;
        padding-top: 10px;
        border-bottom: 1px solid rgba(0, 0, 0, 0.1);
    }

    .shopping-cart .summary .text{
        font-size: 1em;
        font-weight: 600;
    }

    .shopping-cart .summary .price{
        font-size: 1em;
        float: right;
    }

    .shopping-cart .summary button{
        margin-top: 20px;
    }

    @media (min-width: 768px) {
        .shopping-cart .items .product .info {
            padding-top: 25px;
            text-align: left;
        }

        .shopping-cart .items .product .info .price {
            font-weight: bold;
            font-size: 22px;
            top: 17px;
        }

        .shopping-cart .items .product .info .quantity {
            text-align: center;
        }
        .shopping-cart .items .product .info .quantity .quantity-input {
            padding: 4px 10px;
            text-align: center;
        }
    }

</style>
<script type="text/javascript">
    function loadDataCart(id, index) {
        var value = $('.quantityProd').eq(index).val();
        $.ajax({
            type: 'POST',
            url: '/cart/update/'+id+'?quantity='+value,
            success: function(data){
                $('#listCart').html($(data).find('#listCart').children());
                $('#tag-cart').html($(data).find('#tag-cart').children());
            }
        })
    }
</script>
<head>
    <title>Thanh Toán</title>

</head>
<body>
<jsp:include page="header.jsp"/>
<br>
<article>
    <div class="container">
        <div class="row">
            <center>
            <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
                <div class="row">
                    <div class="col-xs-6 col-sm-6 col-md-6">
                        <address>
                            <strong>Địa chỉ nhận hàng</strong>
                            ${sessionScope.logInTK.diachiTK}
                            <br>
                            <p title="Phone">Số điện thoại: ${sessionScope.logInTK.sdtTK}</p>
                        </address>
                    </div>
                    <div class="col-xs-6 col-sm-6 col-md-6 text-right">
                        <p>
                            <em>Date: 1st November, 2013</em>
                        </p>
                        <p>
                            <em>Receipt #: 34522677W</em>
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="text-center">
                        <h1>Receipt</h1>
                    </div>
                    </span>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Product</th>
                            <th>#</th>
                            <th class="text-center">Price</th>
                            <th class="text-center">Total</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="col-md-9"><em>Baked Rodopa Sheep Feta</em></td>
                            <td class="col-md-1" style="text-align: center"> 2 </td>
                            <td class="col-md-1 text-center">$13</td>
                            <td class="col-md-1 text-center">$26</td>
                        </tr>
                        <tr>
                            <td>   </td>
                            <td>   </td>
                            <td class="text-right">
                                <p>
                                    <strong>Subtotal: </strong>
                                </p>
                                <p>
                                    <strong>Tax: </strong>
                                </p></td>
                            <td class="text-center">
                                <p>
                                    <strong>$6.94</strong>
                                </p>
                                <p>
                                    <strong>$6.94</strong>
                                </p></td>
                        </tr>
                        <tr>
                            <td>   </td>
                            <td>   </td>
                            <td class="text-right"><h4><strong>Total: </strong></h4></td>
                            <td class="text-center text-danger"><h4><strong>$31.53</strong></h4></td>
                        </tr>
                        </tbody>
                    </table>
                    <a href="product" class="btn btn-success btn-lg btn-block">
                        Quay lại sản phẩm<span class="glyphicon glyphicon-chevron-right"></span>
                    </a></td>
                </div>
            </div>
            </center>
        </div>
    </div>
</article>
<br>
<jsp:include page="footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/aa540ef16a.js" crossorigin="anonymous"></script>
</body>
</html>
