<?php

namespace AchatBundle\Controller;

use AchatBundle\Entity\LigneCommande;
use AchatBundle\Entity\Panier;
use FOS\UserBundle\Model\User;
use StockBundle\Entity\Produit;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use Symfony\Component\HttpFoundation\Request;

/**
 * Lignecommande controller.
 *
 * @Route("lignecommande")
 */
class LigneCommandeController extends Controller
{
    /**
     * Lists all ligneCommande entities.
     *
     * @Route("/", name="lignecommande_index")
     * @Method("GET")
     */
    public function indexAction()
    {
        $user = $this->getUser();
        $panier=$this->getDoctrine()->getManager()->getRepository(Panier::class)->findByUser_Id($user);
        $em = $this->getDoctrine()->getManager();

        $ligneCommandes = $em->getRepository('AchatBundle:LigneCommande')->findAll();


        return $this->render('@Achat/lignecommande/index.html.twig', array(
            'ligneCommandes' => $ligneCommandes,
            'panier' => $panier

        ));
    }

    /**
     * Creates a new ligneCommande entity.
     *
     * @Route("/{id}/new", name="lignecommande_new")
     * @Method({"GET", "POST"})

     */
    public function newAction(Request $request, $id)
    {
        $produit=$this->getDoctrine()->getManager()->getRepository('StockBundle:Produit')->find($id);

        $user = $this->getUser();

        $panier=$this->getDoctrine()->getManager()->getRepository(Panier::class)->findByUser_Id($user);

        $ligneCommande = $this->getDoctrine()->getManager()->getRepository('AchatBundle:LigneCommande')->findByProduit($produit,$panier);
//dump($panier);
//exit();
        $form = $this->createForm('AchatBundle\Form\LigneCommandeType', $ligneCommande);
        $form->handleRequest($request);

        if ($ligneCommande != null) {

            $ligneCommande->setQuantite(($ligneCommande->getQuantite()+1));
            $panier->setPrixtotal($panier->getPrixtotal() + $produit->getPrix());

            $this->getDoctrine()->getManager()->flush();
        }
        else {
            $ligneCommande = new Lignecommande();
            $ligneCommande->setIdproduit($produit);
            $ligneCommande->setNomproduit($produit->getNom());
            $ligneCommande->setIdPanier($panier);
            $ligneCommande->setQuantite(1);

            $panier->setPrixtotal($panier->getPrixtotal() + ($produit->getPrix() * $ligneCommande->getQuantite()));
        }

            $em = $this->getDoctrine()->getManager();
            $em->persist($ligneCommande);
            $em->flush();

            return $this->redirectToRoute('lignecommande_show', array('id' => $ligneCommande->getId()));

        return $this->render('@Achat/lignecommande/new.html.twig', array(
            'ligneCommande' => $ligneCommande,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a ligneCommande entity.
     *
     * @Route("/{id}", name="lignecommande_show")
     * @Method("GET")
     */
    public function showAction(LigneCommande $ligneCommande)
    {
        $deleteForm = $this->createDeleteForm($ligneCommande);

        return $this->render('@Achat/lignecommande/show.html.twig', array(
            'ligneCommande' => $ligneCommande,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing ligneCommande entity.
     *
     * @Route("/{id}/edit", name="lignecommande_edit")
     * @Method({"GET", "POST"})
     */
    public function editAction(Request $request, LigneCommande $ligneCommande)
    {
//      recuperer la quantite avant la modification
        $qte = $ligneCommande->getQuantite();

        $deleteForm = $this->createDeleteForm($ligneCommande);
        $editForm = $this->createForm('AchatBundle\Form\LigneCommandeType', $ligneCommande);
        $editForm->handleRequest($request);

        $panier=$this->getDoctrine()->getManager()->getRepository(Panier::class)->find($ligneCommande->getIdPanier());

        if ($ligneCommande->getQuantite() >= $qte){
            $panier->setPrixtotal($panier->getPrixtotal() + ($ligneCommande->getQuantite()-$qte)*($ligneCommande->getIdproduit()->getPrix()));
        }else{
            $panier->setPrixtotal($panier->getPrixtotal() - ($qte-$ligneCommande->getQuantite())*($ligneCommande->getIdproduit()->getPrix()));
        }

//        dump($ligneCommande);
//        exit();


//        editform bch traj3elna lignecommande modifiee
        $produit=$this->getDoctrine()->getManager()->getRepository('StockBundle:Produit')->find($ligneCommande->getIdproduit());

        if ($editForm->isSubmitted() && $editForm->isValid()) {
//            itha l'quantite mahich valide bch jraja3na l page edit
            if ($ligneCommande->getQuantite()<0){

                return $this->render('@Achat/lignecommande/edit.html.twig', array(
                    'ligneCommande' => $ligneCommande,
                    'edit_form' => $editForm->createView(),
                    'delete_form' => $deleteForm->createView(),
                ));
            }elseif($produit->getQte() < $ligneCommande->getQuantite()) {
                $ligneCommande->setQuantite($produit->getQte());
                $produit->setQte(0);
            }else{
//          itha valide bch ya3ml maj ta3 bd
                $produit->setQte($produit->getQte() - $ligneCommande->getQuantite());
                $this->getDoctrine()->getManager()->flush();
            }

            return $this->redirectToRoute('lignecommande_index');
        }

        return $this->render('@Achat/lignecommande/edit.html.twig', array(
            'ligneCommande' => $ligneCommande,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a ligneCommande entity.
     *
     * @Route("/{id}/delete", name="lignecommande_delete")
     * @Method("DELETE")
     */
    public function deleteAction(Request $request, LigneCommande $ligneCommande)
    {
        $form = $this->createDeleteForm($ligneCommande);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($ligneCommande);
            $em->flush();
        }

        return $this->redirectToRoute('lignecommande_index');
    }

    /**
     * Creates a form to delete a ligneCommande entity.
     *
     * @param LigneCommande $ligneCommande The ligneCommande entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(LigneCommande $ligneCommande)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('lignecommande_delete', array('id' => $ligneCommande->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }

    /**
     * Deletes a ligneCommande entity.
     *
     * @Route("/{id}/supprimer", name="lignecommande_supprimer")
     * @Method("DELETE")
     */

    public function supprimerAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $lignecommande = $em->getRepository("AchatBundle:LigneCommande")->find($id);

        $produit=$this->getDoctrine()->getManager()->getRepository('StockBundle:Produit')->find($lignecommande->getIdproduit());

        $panier=$this->getDoctrine()->getManager()->getRepository(Panier::class)->find($lignecommande->getIdPanier());
        $panier->setPrixtotal($panier->getPrixtotal() - ($produit->getPrix()*$lignecommande->getQuantite()));

        $em->remove($lignecommande);
        $em->flush();
        return $this->redirectToRoute('lignecommande_index');
    }
}
