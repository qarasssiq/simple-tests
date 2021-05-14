<#import "parts/common.ftlh" as c>

<@c.page "Профиль">
    <div class="row text-center">
        <form method="post">
            <div style="margin-top: 10px">Имя: ${user.username}</div>
            <div style="margin-top: 10px">Роль: ${user.userRole}</div>
            <div style="margin-top: 10px">Рейтинг: ${user.userRating}</div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div style="margin-top: 10px">
                <button type="button">Изменить имя</button>
                <button type="button">Изменить пароль</button>
            </div>
        </form>
    </div>
    <div class="col-lg-6 offset-3 text-center" style="margin-top: 50px">
        <h1 style="margin-bottom: 15px">Мои тесты</h1>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>Название</th>
                <th>Описание</th>
                <th style="width: 50px">Количество вопросов</th>
                <th style="width: 30px">Рейтинг теста</th>
                <th style="width: 130px"></th>
                <th style="width: 100px"></th>
            </tr>
            </thead>
            <tbody>
            <#list tests as test>
                <tr>
                    <td>${test.testName}</td>
                    <td>${test.description}</td>
                    <td>${test.numberOfQuestions}</td>
                    <td>${test.testRating}</td>
                    <td><input type="button" value="Редактировать" onClick='location.href="/tests/test/edit/${test.testId}"'></td>
                    <td><input type="button" class="deleteButton" value="Удалить" onClick='location.href="/tests/test/${test.testId}/delete"'></td>
                </tr>
            <#else>
                Пусто
            </#list>
            </tbody>
        </table>
        <input type="button" value="Создать новый тест" onClick='location.href="/tests/test/create"'>
    </div>
</@c.page>