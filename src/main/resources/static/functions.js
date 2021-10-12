function searchByLogin() {
    let login = document.getElementById("search_field").value;
    let xhtml = new XMLHttpRequest();
    xhtml.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let user = JSON.parse(this.responseText);
            let html = '<tr>\n' +
                '<th>User id</th>\n' +
                '<th>User name</th>\n' +
                '<th>User Email</th>\n' +
                '<th>Delete</th>\n' +
                '<tr>';
            html = html + '<tr><td>' + user.id + '</td>\n' +
                '<td>' + user.name + '</td>\n' +
                '<td>' + user.login + '</td>\n' +
                '<td>' + user.email + '</td>' +
                '<td><button onclick="deleteUser(' + user.id + ')">Delete</button></td></tr>';
            document.getElementById("usersList").innerHTML = html;
        }
    };
    xhtml.open("GET", "http://localhost:8080/users/findByLogin?login=" + login, true);
    xhtml.send();
    document.getElementById("search_field").value = '';
}

function deleteUser(userId) {
    let xhtml = new XMLHttpRequest();
    xhtml.open("DELETE", "http://localhost:8080/users/delete/" + userId, true);
    xhtml.send();
    loadUsers();
    xhtml.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            loadUsers();
        }
    };
}

function createUser() {
    let userName = document.getElementById("user_name").value;
    let userLogin = document.getElementById("user_login").value;
    let userEmail = document.getElementById("user_email").value;

    let xhtml = new XMLHttpRequest();
    xhtml.open("POST", "http://localhost:8080/users/save", true);
    xhtml.setRequestHeader("Content-Type", "application/json");
    xhtml.send(JSON.stringify({name: userName, login: userLogin, email: userEmail}));
    xhtml.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200){
            $(function() {
                console.log( "ready!" );
            });
        }
    }
}

function loadUsers() {
    let xhtml = new XMLHttpRequest();
    xhtml.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let users = JSON.parse(this.responseText);
            let html = '<tr>\n' +
                '        <th>User id</th>\n' +
                '        <th>User name</th>\n' +
                '        <th>User login</th>\n' +
                '        <th>User email</th>\n' +
                '        <th>Delete</th>\n' +
                '    </tr>';
            for (let i = 0; i < users.length; i++) {
                let user = users[i];
                console.log(user);
                html = html + '<tr><td>' + user.id + '</td>\n' +
                    '        <td>' + user.name + '</td>\n' +
                    '        <td>' + user.login + '</td>\n' +
                    '        <td>' + user.email + '</td>' +
                    '        <td><button onclick="deleteUser(' + user.id + ')">Delete</button></td></tr>';
            }
            document.getElementById("usersList").innerHTML = html;
        }
    };
    xhtml.open("GET", "http://localhost:8080/users/findAll", true);
    xhtml.send();
}

loadUsers();