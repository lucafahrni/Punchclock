const URL = "http://localhost:8081/user.html"

let entries= [], categories =[], users =[], role = []

document.addEventListener('DOMContentLoaded', function(){
    const createEntryForm = document.querySelector('#editUserForm');
    createEntryForm.addEventListener('submit', (e) => editUser(e));
});

const login = (e) =>{
    e.preventDefault();
    const formData = new FormData(e.target);
    const user = {};
    user['username'] = formData.get('username');

    fetch(`${URL}/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': localStorage.getItem("Token")
        },
        body: JSON.stringify(user)
    }).then((result) => {
        console.log(result)
        if (result.status == 200){
            localStorage.setItem("Token", result.headers.get("Authorization"));
            window.location.replace(`${URL}/homepage.html`);

        }

    });
}
