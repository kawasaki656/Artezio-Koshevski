<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 26.12.2016
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html ng-app="app">
<head>
    <title>Заявка на инкассацию</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.0/angular-resource.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="<c:url value="/jsp/js/controller.js" />"></script>
    <script src="<c:url value="/jsp/js/resources.js" />"></script>
    <script src="<c:url value="/jsp/js/dictionary.js" />"></script>
</head>
<body ng-controller="control">

<div class="container">
    <!--	!!!!! Авторизация !!!!!	-->
    <nav class="navbar navbar-default" >
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Регистрация заявок на инкассацию</a>
            </div>
            <form class="navbar-form navbar-right" action="http://localhost:8080/" method="post">
                <div class="form-group" style="visibility: hidden">
                    <input type="text" class="form-control" placeholder="Логин" name="login">
                    <input type="password" class="form-control" placeholder="Пароль" name="pass">
                </div>
                <button type="submit" class="btn btn-default" >Выйти</button>
            </form>
        </div>
    </nav>

    <div class="alert alert-success" style="display:{{dispSuccess}}"><b>Заявка успешно добавлена!</b></div>
    <div class="alert alert-warning" style="display:{{dispWarning}}"><b>Создайте сначала заявку!</b></div>

    <!--	!!!!! Новая заявка на инкассацию !!!!!	-->
    <form class="form-horizontal" ng-submit="submitNewRequest()" style="display:{{visNewRequest}}">
        <div class="page-header form-group">
            <div class="col-sm-6">
                <h2 class="control-label col-sm-5" style="text-align:left">Новая заяка</h2>
                <div class="col-sm-5">
                    <select id="disabledSelect" class="form-control" style="margin-top:26px" ng-model="selectedItem" ng-change="update()" data-toggle="tooltip" data-placement="right" title="Тип заявки, выбираемый при формировании ЭД">
                        <option>Добавить</option>
                        <option>Изменить</option>
                        <option>Удалить</option>
                    </select>

                </div>
            </div>
            <br>
        </div>


        <div class="form-group">
            <label class="control-label col-sm-2">Подразделение банка:</label>
            <div class="col-sm-4">
                <select id="disabledSelect" class="form-control" data-toggle="tooltip" title="Подразделения банка, где обслуживается клиент" ng-model="bank">
                    <option>BelarusBank</option>
                    <option>BelinvestBank</option>
                    <option>Moscow Minsk</option>
                </select>
            </div>

            <label class="control-label col-sm-2" >ИНН/КИО:</label>
            <div class="col-sm-4">
                <input class="form-control" type="number" ng-model="inn"></input>
            </div>

        </div>

        <div class="form-group">
            <label class="control-label col-sm-2">КПП:</label>
            <div class="col-sm-4">
                <input type="text" maxlength="9" class="form-control" data-toggle="tooltip" data-placement="right" ng-model="kpp"></input>
            </div>

            <label class="control-label col-sm-2">Полное наименование организации:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" data-toggle="tooltip" data-placement="right" ng-model="nameOrganization"></input>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2">ОГРН:</label>
            <div class="col-sm-4">
                <input type="number" class="form-control" ng-model="ogrn"></input>
            </div>

            <label class="control-label col-sm-2">Имя уполномоченного сотрудника:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" data-toggle="tooltip" data-placement="right" title="ФИО, Должность" ng-model="nameEmploye"></input>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2">Номер телефона уполномоченного:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" data-toggle="tooltip" data-placement="right" title="Номер телефона для связи" ng-model="telephoneEmploye"></input>
            </div>

            <label class="control-label col-sm-2">Иные реквизиты банка:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" data-toggle="tooltip" data-placement="right" title="Указывается дополнительная информация" ng-model="bankDetails"></input>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" >Номер счёта зачисления:</label>
            <div class="col-sm-4">
                <input maxlength="20" class="form-control" data-toggle="tooltip" data-placement="right" title="Номер счета клиента в банке, на который требуется зачислить инкассированные денежные средства" ng-model="accountNumber"></input>
            </div>

            <label class="control-label col-sm-2">БИК:</label>
            <div class="col-sm-4">
                <input type="text" maxlength="9" class="form-control" data-toggle="tooltip" data-placement="right" title="БИК банка, в который требуется инкассировать денежные средства" ng-model="bik"></input>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" >Номер счёта банка:</label>
            <div class="col-sm-4">
                <input maxlength="20" class="form-control" data-toggle="tooltip" data-placement="right" title="Счет и реквизиты банка, на который производится перечисление проинкассированных средств. " ng-model="bankNumber"></input>
            </div>

            <label class="control-label col-sm-2">Наименование банка:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" data-toggle="tooltip" data-placement="right" title="Наименование банка, в который требуется инкассировать денежные средства" ng-model="nameBank"></input>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" >SWIFT:</label>
            <div class="col-sm-4">
                <input class="form-control" data-toggle="tooltip" data-placement="right" title="SWIFT банка, в который требуется инкассировать денежные средства" pattern="^([a-zA-Z\d]{8})|([a-zA-Z\d]{12})$" maxlength="12" ng-model="swift"></input>
            </div>

            <label class="control-label col-sm-2" >Объект инкассации:</label>
            <div class="col-sm-4">
                <button type="button" class="btn btn-default" ng-click="addIncas()">Добавить</button>
                <img src="<c:url value="/jsp/img/success.png" />" height="30px" style="visibility:{{visAddSuccess}}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-2">
                <input class="form-control btn-success" type="submit" ></input>
            </div>
        </div>
    </form>


    <!--	!!!!! Добавить объект инкассации !!!!!	-->
    <div class="col-sm-12" style="display:{{visAddIncas}}">
        <div class="page-header col-sm-12">
            <div >
                <h2 class="control-label col-sm-5" style="text-align:left">Добавить объект инкассации:</h2>
            </div><br>
        </div>
        <form class="form-horizontal" ng-submit="sendIncas()" >
            <div class="form-group">
                <label class="control-label col-sm-2">Желательное время сдачи наличных:</label>
                <div class="col-sm-4">
                    <input class="form-control" type="time" ng-model="time" required></input>
                </div>

                <label class="control-label col-sm-2">Способ сдачи денежнйо наличности:</label>
                <div class="col-sm-4">
                    <select class="form-control"  ng-model="typeOfPutting" data-toggle="tooltip">
                        <option>По объявлению на взнос наличными</option>
                        <option>В инкассаторских сумках</option>
                        <option>Через службу инкассации</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" >Переодичность оказания услуг:</label>
                <div class="col-sm-4">
                    <select class="form-control"  ng-model="periodOfService" >
                        <option>Ежедневно</option>
                        <option>Рабочие дни</option>
                        <option>Через день</option>
                        <option>День недели</option>
                        <option>По заявке</option>
                        <option>По звонку</option>
                    </select>
                </div>

                <label class="control-label col-sm-2" >День недели:</label>
                <div class="col-sm-4">
                    <select class="form-control"  ng-model="dayOfWeek" >
                        <option>Понедельник</option>
                        <option>Вторник</option>
                        <option>Среда</option>
                        <option>Четверг</option>
                        <option>Пятница</option>
                        <option>Суббота</option>
                        <option>Воскресенье</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" >Предполагаемый оъем денежной наличности:</label>
                <div class="col-sm-4">
                    <input class="form-control" data-toggle="tooltip" data-placement="right" title="Сумма предназначенная клиентом к инкассации. М.б. как в рублях так и в ин. валюте" type="number" ng-model="countOfMoney" required></input>
                </div>

                <label class="control-label col-sm-2">Код валюты:</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" data-toggle="tooltip" data-placement="right" title="Буквенный код валюты (в соответствии со стандартом ISO 4217)" maxlength="3" ng-model="codeOfCurrency" required></input>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" >Руководитель объекта:</label>
                <div class="col-sm-4">
                    <input class="form-control" data-toggle="tooltip" data-placement="right" title="Контактный телефон руководителя точки юр. лица, по которой подразделение инкассации производит инкассацию"  ng-model="telephoneHead" required></input>
                </div>

                <label class="control-label col-sm-2">Желательная дата начала обслуживания:</label>
                <div class="col-sm-4">
                    <input type="date" class="form-control" data-toggle="tooltip" data-placement="right" title="ДД.ММ.ГГГГ" ng-model="date" required></input>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-2">
                    <input class="form-control btn-success" type="submit" value="Добавить" ></input>
                </div>

                <div class="col-sm-4">
                </div>


                <div class="col-sm-6">
                    <table class="table table-condensed" >
                        <thead>
                        <tr>
                            <th>Номер заявки</th>
                            <th>Руководитель</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ng-repeat="obj in objects">
                            <td>{{obj.id + 1}}</td>
                            <td>{{obj.telephoneHead}}</td>
                        </tr>
                        </tbody>

                    </table>
                </div>
            </div>


        </form>
    </div>

    <!--	!!!!! Список заявок на инкассацию !!!!!	-->
    <div class="col-sm-12" style="display:{{visTableIncas}}">
        <div class="page-header col-sm-12">
            <div >
                <h2 class="control-label col-sm-5" style="text-align:left">Спиок заявок на инкассацию:</h2>
            </div>
            <br>
        </div>
        <div class="col-sm-3">
            <form>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-search"></span>
                        </div>
                        <input type="text" class="form-control" placeholder="Поиск" ng-model="search">
                    </div>
                </div>
            </form>
        </div>

        <table class="table table-hover table-bordered" >
            <thead>
            <tr>
                <th>
                    <a href="#" ng-click="sortReverse=!sortReverse">
                        Номер заявки
                        <span ng-show="!sortReverse" class="caret caret-down"></span>
                        <span ng-show="sortReverse" class="caret caret-up"></span>
                    </a>
                <th>Дата заявки</th>
                <th>Статус ЭД</th>
                <th>Тип заявки</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="Request in requests | filter:search | orderBy:id:sortReverse" ng-click="clickRow(Request)">
                <td>{{Request.id}}</td>
                <td>{{Request.date}}</td>
                <td>{{Request.status}}</td>
                <td>{{Request.type}}</td>
            </tr>
            </tbody>

        </table>


        <div class="col-sm-2">
            <input class="form-control btn-success" type="button" value="Добавить" ng-click="addReq()"></input>
        </div>


    </div>
</div>

<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip({trigger: "focus",delay: 1000});
    });
</script>

</body>
</html>
