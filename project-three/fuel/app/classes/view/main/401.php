<!-- Marshall Bowers -->
<?php
    class View_Main_401 extends ViewModel {
        public function view() {
            $this->title = '401 &raquo; CSC417';
            $this->user = Session::get('user');
        }
    }
?>
