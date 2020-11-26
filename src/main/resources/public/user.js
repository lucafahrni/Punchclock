const URL = "http://localhost:8081/user.html"

let password =[], username =[], role = []

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#editUserForm');
    createEntryForm.addEventListener('submit', (e) => updateUser(e));
});

const updateUser = (e) =>{
    e.preventDefault();
    const formData = new FormData(e.target);
    const user = {};
    user['username'] = formData.get('username');
    user['password'] = formData.get('password');
    user['role'] = formData.get('role');

    fetch(`${URL}/users`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem("Token")
        },
        body: JSON.stringify({username, password, role})
    }).then((result) => {
        console.log(result)
        if (result.status == 200){
            localStorage.setItem("Token", result.headers.get("Authorization"));
            window.location.replace(`${URL}/homepage.html`);

        }

    });
}
