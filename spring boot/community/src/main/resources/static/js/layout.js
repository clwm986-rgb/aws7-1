class AppHeader extends HTMLElement {
    async connectedCallback() {
        try {
            const response = await fetch('/include/header.html');
            if (response.ok) {
                this.innerHTML = await response.text();
            }
        } catch (error) {
			this.innerHTML = '<h1>헤더 불러오기에 실패했습니다.</h1>';
            console.error('헤더 로드 실패:', error);
        }
    }
}

customElements.define('app-header', AppHeader);