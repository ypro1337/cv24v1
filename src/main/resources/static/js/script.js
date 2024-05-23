function deleteCv(id) {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce CV?')) {
        fetch(`http://localhost:8080/cv24/delete?id=${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('CV supprimé avec succès.');
                    window.location.reload();
                } else {
                    alert('Erreur lors de la suppression du CV.');
                }
            })
            .catch(error => {
                console.error('Erreur:', error);
                alert('Erreur lors de la suppression du CV.');
            });
    }
}